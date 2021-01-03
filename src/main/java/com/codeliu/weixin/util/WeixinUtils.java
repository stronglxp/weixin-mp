package com.codeliu.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.codeliu.weixin.entity.*;
import com.codeliu.weixin.entity.message.*;
import com.codeliu.weixin.exception.AesException;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WeixinUtils {
    private static String appID = "wx827dab48c2a9e0f9";
//    @Value("${appID}")
//    private String appId;

    private static String appSecret = "c10b1a200cc711c37451c4af7c384667";
//    @Value("${appsecret}")
////    private String secret;

    private static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 存储token
    private static AccessToken at;

//    @PostConstruct
//    public void init() {
//        appID = appId;
//        appSecret = secret;
//    }

    /**
     * 获取access_token
     */
    private static void getToken() {
        String url = GET_TOKEN_URL.replace("APPID", appID).replace("APPSECRET", appSecret);
        String accessToken = get(url);
        JSONObject object = JSONObject.parseObject(accessToken);
        String token = object.getString("access_token");
        String expiresIn = object.getString("expires_in");
        at = new AccessToken(token, expiresIn);
    }

    /**
     * 外部调用这个方法获取access_token
     * @return
     */
    public static String getAccessToken() {
        if (at == null || at.isExpire()) {
            getToken();
        }

        return at.getAccessToken();
    }

    /**
     * 携带数据发送post请求
     * @param url
     * @param data
     * @return
     */
    public static String post(String url, String data) {
        try {
            URL urlObj = new URL(url);
            // 打开链接
            URLConnection connection = urlObj.openConnection();
            // 可携带数据
            connection.setDoOutput(true);
            // 获取输出流
            OutputStream os = connection.getOutputStream();
            // 写数据
            os.write(data.getBytes());
            os.close();

            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 发送get请求
     * @param url
     * @return
     */
    public static String get(String url) {
        try {
            URL urlObj = new URL(url);
            // 打开链接
            URLConnection connection = urlObj.openConnection();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = is.read(b)) != -1) {
                sb.append(new String(b, 0, len));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证微信服务器URL
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkServer(String signature, String timestamp, String nonce) {
        String msg = null;
        try {
            msg = SHA1Utils.getSHA1(timestamp, nonce);
            if (msg != null && msg.equals(signature)) {
                return true;
            }
        } catch (AesException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析xml数据包
     *
     * @param is
     * @return
     */
    public static Map<String, String> parseXML(InputStream is) {
        SAXReader reader = new SAXReader();
        Map<String, String> res = new HashMap<>();
        try {
            // 读取输入流，获取文档对象
            Document document = reader.read(is);
            // 获取文档根节点
            Element root = document.getRootElement();
            // 获取根节点的所有子节点
            List<Element> elements = root.elements();
            for (Element e : elements) {
                res.put(e.getName(), e.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 处理所有的事件和消息回复
     * @param msg 用户输入的消息
     * @return 回复内容字符串
     */
    public static String getRespose(Map<String, String> msg) {
        BaseMessage baseMessage = null;
        String msgType = msg.get("MsgType");
        switch (msgType) {
            case MessageType.REQ_MESSAGE_TYPE_TEXT:
                baseMessage = dealTextMessage(msg);
                break;
            case MessageType.REQ_MESSAGE_TYPE_IMAGE:

                break;
            case MessageType.REQ_MESSAGE_TYPE_VOICE:

                break;
            case MessageType.REQ_MESSAGE_TYPE_VIDEO:

                break;
            case MessageType.REQ_MESSAGE_TYPE_EVENT:
                baseMessage = dealEvent(msg);
                break;
            default:
                break;
        }

        if (baseMessage != null) {
            return messageToXml(baseMessage);
        }
        return null;
    }

    /**
     * 处理事件推送
     */
    private static BaseMessage dealEvent(Map<String, String> msg) {
        String event = msg.get("Event");
        switch (event) {
            // 点击
            case MessageType.EVENT_TYPE_CLICK:
                return dealClick(msg);
            // 跳转
            case MessageType.EVENT_TYPE_VIEW:
                break;
            // 订阅
            case MessageType.EVENT_TYPE_SUBSCRIBE:
                return new TextMessage(msg, "<a href='http://www.baidu.com'>请注册</a>");
        }
        return null;
    }

    /**
     * 处理click菜单
     * @param msg
     * @return
     */
    private static BaseMessage dealClick(Map<String, String> msg) {
        // 创建菜单时传入的key
        String key = msg.get("EventKey");
        switch (key) {
            case "1":
              return new TextMessage(msg, "你点了菜单");
        }
        return null;
    }

    /**
     * 消息对象转换为xml
     * @param baseMessage
     * @return
     */
    private static String messageToXml(BaseMessage baseMessage) {
        XStream stream = new XStream();
        // 处理@XStreamAlias注解
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ArticleMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        // 将map对象转为xml
        String res = stream.toXML(baseMessage);
        return res;
    }

    /**
     * 处理文本消息类型
     * @param msg
     * @return
     */
    private static BaseMessage dealTextMessage(Map<String, String> msg) {
        // 用户发来的内容
        String content = msg.get("Content");
        if (content != null && content.equals("图文")) {
            List<Article> articles = new ArrayList<>();
            articles.add(new Article("你好", "点击查看详情", "http://mmbiz.qpic.cn/mmbiz_jpg/IG1SMHXtjCObeqLmvzH4ScmF8KeK7CBwkkKJ1XLgEHMWibq7Sf5sY9iad1TyITRTdw3OULicSAYhIccgKuh2a0Duw/0", "http://www.baidu.com"));
            ArticleMessage message = new ArticleMessage(msg, articles);
            return message;
        }
        TextMessage textMessage = new TextMessage(msg, "干啥子？");
        return textMessage;
    }
}

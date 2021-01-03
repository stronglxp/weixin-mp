package com.codeliu.weixin;

import com.alibaba.fastjson.JSONObject;
import com.codeliu.weixin.controller.MaterialController;
import com.codeliu.weixin.entity.button.*;
import com.codeliu.weixin.entity.message.*;
import com.codeliu.weixin.controller.TemplateMessageController;
import com.codeliu.weixin.util.WeixinUtils;
import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class WeixinApplicationTests {

    @Test
    void testUploadTemporaryMaterial() {
        MaterialController materialController = new MaterialController();
        String path = "C:\\Users\\Administrator\\Desktop\\1.jpg";
        String res = materialController.uploadTemporaryMaterial(path, "image");
        System.out.println(res);

        JSONObject object = JSONObject.parseObject(res);
        res = materialController.getTemporaryMaterial(object.get("media_id").toString());
        System.out.println(res);
    }

    @Test
    void testSendTemplateMessage() {
        TemplateMessageController messageManage = new TemplateMessageController();
        String res = messageManage.sendTemplateMessage();
        System.out.println(res);
    }

    @Test
    void testIndustry() {
        TemplateMessageController messageManage = new TemplateMessageController();
        messageManage.setIndustry();
        System.out.println(messageManage.getIndustry());
    }

    @Test
    void testButton() {
        // 菜单对象
        Button button = new Button();
        // 第一个一级菜单
        button.getButton().add(new ClickButton("一级点击", "1"));
        // 第二个一级菜单
        button.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
        // 第三个一级菜单
        SubButton sb = new SubButton("有子菜单");
        // 子菜单
        sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
        sb.getSub_button().add(new MiniProgramButton("我的信息", "", ""));
        // 加入第三个一级菜单
        button.getButton().add(sb);

        Object object = JSONObject.toJSON(button);
        System.out.println(object.toString());
    }

    @Test
    void testAccessToken() {
        System.out.println(WeixinUtils.getAccessToken());
        System.out.println(WeixinUtils.getAccessToken());
    }

    @Test
    void contextLoads() {
        Map<String, String> msg = new HashMap<>();
        msg.put("ToUserName", "to");
        msg.put("FromUserName", "from");
        msg.put("MsgType", "type");
        TextMessage message = new TextMessage(msg, "干山额？");

        XStream stream = new XStream();
        // 处理@XStreamAlias注解
        stream.processAnnotations(TextMessage.class);
        stream.processAnnotations(ArticleMessage.class);
        stream.processAnnotations(ImageMessage.class);
        stream.processAnnotations(MusicMessage.class);
        stream.processAnnotations(VideoMessage.class);
        stream.processAnnotations(VoiceMessage.class);
        // 将map对象转为xml
        String res = stream.toXML(message);
        System.out.println(res);
    }

}

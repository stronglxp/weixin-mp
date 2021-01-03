package com.codeliu.weixin.controller;

import com.codeliu.weixin.util.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 素材管理
 */
@RestController
@RequestMapping("/material")
public class MaterialController {
    private Logger logger = LoggerFactory.getLogger(MaterialController.class);

    /**
     * 上传临时素材
     * @param path 素材路径
     * @param type 素材类型
     * @return
     */
    @PostMapping("/upload/temporary")
    public String uploadTemporaryMaterial(String path, String type) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", WeixinUtils.getAccessToken()).replace("TYPE", type);
        File file = new File(path);

        try {
            URL urlObj = new URL(url);
            // 安全连接
            HttpsURLConnection conn = (HttpsURLConnection)urlObj.openConnection();

            // 设置连接信息
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);

            // 设置请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "utf8");

            // 数据边界
            String boundary = "-----" + System.currentTimeMillis();
            conn.setRequestProperty("Content-type", "multipart/form-data;boundary=" + boundary);

            // 获取输出流
            OutputStream out = conn.getOutputStream();
            // 创建文件的输入流
            InputStream is = new FileInputStream(file);

            // 第一部分，头部信息
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
            sb.append("Content-type:application/octet-stream\r\n\r\n");
            out.write(sb.toString().getBytes());

            // 第二部分，文件内容
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                out.write(b, 0 ,len);
            }

            // 第三部分，尾部信息
            String foot = "\r\n--" + boundary + "--\r\n";
            out.write(foot.getBytes());
            out.flush();
            out.close();

            // 读取数据
            InputStream inputStream = conn.getInputStream();
            StringBuilder resp = new StringBuilder();
            while ((len = inputStream.read(b)) != -1) {
                resp.append(new String(b, 0, len));
            }

            return resp.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取临时素材
     * @param mediaId 媒体文件ID
     * @return
     */
    @GetMapping("/get/temporary")
    public String getTemporaryMaterial(String mediaId) {
        String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        url = url.replace("ACCESS_TOKEN", WeixinUtils.getAccessToken()).replace("MEDIA_ID", mediaId);
        String res = WeixinUtils.get(url);
        return res;
    }
}

package com.codeliu.weixin.controller;

import com.codeliu.weixin.util.WeixinUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/weixin")
    public String authWeixinServer(@RequestParam("signature") String signature,
                                   @RequestParam("timestamp") String timestamp,
                                   @RequestParam("nonce") String nonce,
                                   @RequestParam("echostr") String echostr) {
        if (WeixinUtils.checkServer(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    /**
     * 接收消息和事件推送
     * @return
     */
    @PostMapping("/weixin")
    public String getRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            // 获取用户输入的消息文本，转化成字符串
            Map<String, String> msg = WeixinUtils.parseXML(request.getInputStream());
            System.out.println(msg);
            // 回复用户的文本
            String replyMessage = WeixinUtils.getRespose(msg);
            System.out.println(replyMessage);
            // 响应消息
            return replyMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

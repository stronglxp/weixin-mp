package com.codeliu.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.codeliu.weixin.entity.template.DataValue;
import com.codeliu.weixin.entity.template.MiniProgram;
import com.codeliu.weixin.entity.template.TemplateMessage;
import com.codeliu.weixin.util.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息
 */
@RestController
@RequestMapping("/template")
public class TemplateMessageController {

    private Logger logger = LoggerFactory.getLogger(TemplateMessageController.class);

    /**
     * 设置服务号所属行业
     */
    @PostMapping("/industry")
    public void setIndustry() {
        String accessToken = WeixinUtils.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + accessToken;
        String data = "{\n" +
                "    \"industry_id1\":\"1\",\n" +
                "    \"industry_id2\":\"2\"\n" +
                "}";
        String res = WeixinUtils.post(url, data);
    }

    /**
     * 获取服务号所属行业
     * @return
     */
    @GetMapping("/industry")
    public String getIndustry() {
        String accessToken = WeixinUtils.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + accessToken;
        String res = WeixinUtils.get(url);
        return res;
    }

    /**
     * 发送模板消息
     * @return
     */
    @PostMapping("/message")
    public String sendTemplateMessage() {
        String accessToken = WeixinUtils.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        // MiniProgram miniProgram = new MiniProgram("wx9f66b707a298a38c", "pages/userCenter/index");

        Map<String, DataValue> value = new HashMap<>();
        DataValue dataValue = new DataValue("订单状态通知", "#173177");
        value.put("first", dataValue);
        dataValue = new DataValue("15566666666", "#173177");
        value.put("keyword1", dataValue);
        dataValue = new DataValue("2021-01-03", "#173177");
        value.put("keyword2", dataValue);
        dataValue = new DataValue("已付款", "#173177");
        value.put("keyword3", dataValue);
        dataValue = new DataValue("到达", "#173177");
        value.put("keyword4", dataValue);
        dataValue = new DataValue("感谢您的使用", "#173177");
        value.put("remark", dataValue);

        TemplateMessage templateMessage = new TemplateMessage();
        templateMessage.setTouser("osCyo5_gC8S6TN0uARYOk-ZEkZV8");
        templateMessage.setTemplate_id("GGxSF_MSYFLxNWWCHfHJlw3Vfcxym8lXitPxg0CBPgg");
        // templateMessage.setMiniprogram(miniProgram);
        templateMessage.setUrl("http://www.baidu.com");
        templateMessage.setData(value);

        Object data = JSONObject.toJSON(templateMessage);
        String res = WeixinUtils.post(url, data.toString());
        logger.debug("res = {}" + res);

        return res;
    }
}

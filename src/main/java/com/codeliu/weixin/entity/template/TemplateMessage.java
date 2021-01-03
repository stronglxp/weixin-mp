package com.codeliu.weixin.entity.template;

import lombok.Data;

import java.util.Map;

/**
 * 模板消息类
 */
@Data
public class TemplateMessage {
    private String touser;
    private String template_id;
    private String url;
    private MiniProgram miniprogram;
    private Map<String, DataValue> data;

    public TemplateMessage() {}

    public TemplateMessage(String touser, String template_id, String url, MiniProgram miniprogram, Map<String, DataValue> data) {
        this.touser = touser;
        this.template_id = template_id;
        this.url = url;
        this.miniprogram = miniprogram;
        this.data = data;
    }
}

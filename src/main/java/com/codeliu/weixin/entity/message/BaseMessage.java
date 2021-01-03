package com.codeliu.weixin.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

/**
 * 回复用户消息文本基类
 */
@Data
public class BaseMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private String createTime;
    @XStreamAlias("MsgType")
    private String msgType;

    public BaseMessage(Map<String, String> msg) {
        this.toUserName = msg.get("FromUserName");
        this.fromUserName = msg.get("ToUserName");
        this.createTime = System.currentTimeMillis() + "";
    }
}

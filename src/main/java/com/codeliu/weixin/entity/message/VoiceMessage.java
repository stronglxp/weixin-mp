package com.codeliu.weixin.entity.message;

import com.codeliu.weixin.util.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 语音消息
 *
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[voice]]></MsgType>
 *   <Voice>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *   </Voice>
 * </xml>
 */
@Data
@EqualsAndHashCode(callSuper = false)
// 将对象转成xml时，根节点的名称
@XStreamAlias("xml")
public class VoiceMessage extends BaseMessage {
    @XStreamAlias("Voice")
    private Voice voice;

    public VoiceMessage(Map<String, String> msg, Voice voice) {
        super(msg);
        // 设置消息类型为voice
        this.setMsgType(MessageType.RESP_MESSAGE_TYPE_Voice);
        this.voice = voice;
    }
}

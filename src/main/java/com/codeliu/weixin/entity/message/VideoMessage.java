package com.codeliu.weixin.entity.message;

import com.codeliu.weixin.util.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 视频消息
 *
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[video]]></MsgType>
 *   <Video>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *     <Title><![CDATA[title]]></Title>
 *     <Description><![CDATA[description]]></Description>
 *   </Video>
 * </xml>
 */
@Data
@EqualsAndHashCode(callSuper = false)
// 将对象转成xml时，根节点的名称
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    @XStreamAlias("Video")
    private Video video;

    public VideoMessage(Map<String, String> msg, Video video) {
        super(msg);
        // 设置消息类型为video
        this.setMsgType(MessageType.RESP_MESSAGE_TYPE_Video);
        this.video = video;
    }
}

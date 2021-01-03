package com.codeliu.weixin.entity.message;

import com.codeliu.weixin.util.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 图片消息
 *
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[image]]></MsgType>
 *   <Image>
 *     <MediaId><![CDATA[media_id]]></MediaId>
 *   </Image>
 * </xml>
 */
@Data
@EqualsAndHashCode(callSuper = false)
// 将对象转成xml时，根节点的名称
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    @XStreamAlias("Image")
    private Image image;

    public ImageMessage(Map<String, String> msg, Image image) {
        super(msg);
        // 设置消息类型为image
        this.setMsgType(MessageType.RESP_MESSAGE_TYPE_Image);
        this.image = image;
    }
}

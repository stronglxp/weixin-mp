package com.codeliu.weixin.entity.message;

import com.codeliu.weixin.util.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 音乐消息
 *
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[music]]></MsgType>
 *   <Music>
 *     <Title><![CDATA[TITLE]]></Title>
 *     <Description><![CDATA[DESCRIPTION]]></Description>
 *     <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
 *     <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
 *     <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
 *   </Music>
 * </xml>
 */
@Data
@EqualsAndHashCode(callSuper = false)
// 将对象转成xml时，根节点的名称
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
    @XStreamAlias("Music")
    private Music music;

    public MusicMessage(Map<String, String> msg, Music music) {
        super(msg);
        // 设置消息类型为music
        this.setMsgType(MessageType.RESP_MESSAGE_TYPE_MUSIC);
        this.music = music;
    }
}

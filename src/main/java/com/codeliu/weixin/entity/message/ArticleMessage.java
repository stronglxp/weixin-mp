package com.codeliu.weixin.entity.message;

import com.codeliu.weixin.util.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图文消息
 *
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[news]]></MsgType>
 *   <ArticleCount>1</ArticleCount>
 *   <Articles>
 *     <item>
 *       <Title><![CDATA[title1]]></Title>
 *       <Description><![CDATA[description1]]></Description>
 *       <PicUrl><![CDATA[picurl]]></PicUrl>
 *       <Url><![CDATA[url]]></Url>
 *     </item>
 *   </Articles>
 * </xml>
 */
@Data
@EqualsAndHashCode(callSuper = false)
// 将对象转成xml时，根节点的名称
@XStreamAlias("xml")
public class ArticleMessage extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles = new ArrayList<>();

    public ArticleMessage(Map<String, String> msg, List<Article> articles) {
        super(msg);
        // 设置消息类型为news
        this.setMsgType(MessageType.RESP_MESSAGE_TYPE_NEWS);
        this.articleCount = articles.size() + "";
        this.articles = articles;
    }
}

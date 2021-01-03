package com.codeliu.weixin.entity.button;

import lombok.Data;

/**
 * 小程序类型的button
 */
@Data
public class MiniProgramButton extends AbstractButton {
    private String type;
    private String url;
    // 小程序的appid（仅认证公众号可配置）
    private String appid;
    // 小程序的页面路径
    private String pagepath;

    public MiniProgramButton(String name, String appid, String pagepath) {
        super(name);
        this.type = "miniprogram";
        this.url = "http://mp.weixin.qq.com";
        this.appid = appid;
        this.pagepath = pagepath;
    }
}

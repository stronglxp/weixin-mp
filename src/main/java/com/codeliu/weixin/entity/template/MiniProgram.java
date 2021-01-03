package com.codeliu.weixin.entity.template;

import lombok.Data;

@Data
public class MiniProgram {
    private String appid;
    private String pagepath;

    public MiniProgram(String appid, String pagepath) {
        this.appid = appid;
        this.pagepath = pagepath;
    }
}

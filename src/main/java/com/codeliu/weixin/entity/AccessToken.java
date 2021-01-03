package com.codeliu.weixin.entity;

import lombok.Data;

/**
 * 存放access_token
 */
@Data
public class AccessToken {
    private String accessToken;
    // 过期时间
    private long expiresTime;

    public AccessToken(String accessToken, String expiresIn) {
        this.accessToken = accessToken;
        // 计算出过期时间
        expiresTime = System.currentTimeMillis() + Integer.parseInt(expiresIn) * 1000;
    }

    /**
     * 判断token是否过期
     * @return
     */
    public boolean isExpire() {
        return System.currentTimeMillis() > expiresTime;
    }
}

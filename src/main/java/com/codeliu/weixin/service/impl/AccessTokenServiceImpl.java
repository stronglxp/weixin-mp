package com.codeliu.weixin.service.impl;

import com.codeliu.weixin.service.AccessTokenService;
import com.codeliu.weixin.util.WeixinUtils;
import org.springframework.stereotype.Service;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    /**
     * 获取access_token
     * @return
     */
    @Override
    public String getAccessToken() {
        return WeixinUtils.getAccessToken();
    }
}

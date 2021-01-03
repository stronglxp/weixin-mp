package com.codeliu.weixin.controller;

import com.alibaba.fastjson.JSONObject;
import com.codeliu.weixin.entity.button.*;
import com.codeliu.weixin.util.WeixinUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义菜单
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    private Logger logger = LoggerFactory.getLogger(MenuController.class);

    @PostMapping("/create")
    public String createMenu() {
        // 菜单对象
        Button button = new Button();
        // 第一个一级菜单
        button.getButton().add(new ClickButton("一级点击", "1"));
        // 第二个一级菜单
        button.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
        // 第三个一级菜单
        SubButton sb = new SubButton("有子菜单");
        // 子菜单
        sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
        sb.getSub_button().add(new ClickButton("注册", "32"));
//        sb.getSub_button().add(new MiniProgramButton("我的信息", "wx9f66b707a298a38c", "pages/userCenter/index"));
        // 加入第三个一级菜单
        button.getButton().add(sb);

        Object object = JSONObject.toJSON(button);
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
        url = url.replace("ACCESS_TOKEN", WeixinUtils.getAccessToken());
        // 发送请求
        String result = WeixinUtils.post(url, object.toString());
        logger.debug("result = {}" + result);
        return result;
    }
}

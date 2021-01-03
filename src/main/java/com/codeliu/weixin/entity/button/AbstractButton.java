package com.codeliu.weixin.entity.button;

import lombok.Data;

/**
 * 菜单，菜单类型都继承这个类
 */
@Data
public abstract class AbstractButton {
    private String name;

    public AbstractButton(String name) {
        this.name = name;
    }
}

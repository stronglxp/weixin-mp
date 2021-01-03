package com.codeliu.weixin.entity.button;

import lombok.Data;

/**
 * click类型的button
 */
@Data
public class ClickButton extends AbstractButton {
    private String type;
    private String key;

    public ClickButton(String name, String key) {
        super(name);
        this.type = "click";
        this.key = key;
    }
}

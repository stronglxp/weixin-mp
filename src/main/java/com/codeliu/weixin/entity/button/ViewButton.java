package com.codeliu.weixin.entity.button;

import lombok.Data;

/**
 * view类型的button
 */
@Data
public class ViewButton extends AbstractButton {
    private String type;
    private String url;

    public ViewButton(String name, String url) {
        super(name);
        this.type = "view";
        this.url = url;
    }
}

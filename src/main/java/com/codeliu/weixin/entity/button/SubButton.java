package com.codeliu.weixin.entity.button;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 二级菜单
 */
@Data
public class SubButton extends AbstractButton {
    private List<AbstractButton> sub_button = new ArrayList<>();

    public SubButton(String name) {
        super(name);
    }
}

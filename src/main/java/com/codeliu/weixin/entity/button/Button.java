package com.codeliu.weixin.entity.button;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单，包含一个数组
 */
@Data
public class Button {
    private List<AbstractButton> button = new ArrayList<>();
}

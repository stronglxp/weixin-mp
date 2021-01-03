package com.codeliu.weixin.entity.template;

import lombok.Data;

@Data
public class DataValue {
    private String value;
    private String color;

    public DataValue(String value, String color) {
        this.value = value;
        this.color = color;
    }
}

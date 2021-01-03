package com.codeliu.weixin.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
public class Voice {
    @XStreamAlias("MediaId")
    private String mediaId;
}

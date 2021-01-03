package com.codeliu.weixin.entity.button;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 拍照或者相册发图的button
 */
@Data
public class PhotoOrAlbumButton extends AbstractButton {
    private String type;
    private String key;
    private List<AbstractButton> sub_button = new ArrayList<>();

    public PhotoOrAlbumButton(String name, String key) {
        super(name);
        this.type = "pic_photo_or_album";
        this.key = key;
    }
}

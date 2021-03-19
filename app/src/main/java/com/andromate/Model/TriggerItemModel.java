package com.andromate.Model;

import android.content.Intent;

public class TriggerItemModel {

    String title;
    Integer image;

    public TriggerItemModel(String title, Integer image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}

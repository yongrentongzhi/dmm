package com.dmm.common.pojo;

import java.io.Serializable;

public class Content implements Serializable {
    private String price;
    private String title;
    private String img;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Content{" +
                "price='" + price + '\'' +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

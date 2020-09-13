package com.dmm.common.pojo.goods;


import java.io.Serializable;

public class Spu  implements Serializable {
    private Long id;
    private Long categoryId1;
    private Long categoryId2;
    private String title;
    private String subtitle;
    private String images;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Long categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Spu{" +
                "id=" + id +
                ", categoryId1=" + categoryId1 +
                ", categoryId2=" + categoryId2 +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", images='" + images + '\'' +
                ", status=" + status +
                '}';
    }
}

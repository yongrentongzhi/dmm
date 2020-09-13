package com.dmm.common.pojo.goods;


import java.io.Serializable;

public class Category implements Serializable {
    private Long id;
    //parentId==0时表示一级分类。
    private Long parentId1;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId1() {
        return parentId1;
    }

    public void setParentId1(Long parentId1) {
        this.parentId1 = parentId1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId1=" + parentId1 +
                ", name='" + name + '\'' +
                '}';
    }
}

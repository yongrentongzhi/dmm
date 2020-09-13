package com.dmm.common.pojo.goods;


import java.io.Serializable;

public class Spec  implements Serializable {
    private Long id;
    private String name;
    private Long spuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTemplateId() {
        return spuId;
    }

    public void setTemplateId(Long spuId) {
        this.spuId = spuId;
    }

    @Override
    public String toString() {
        return "Spec{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", spuId=" + spuId +
                '}';
    }
}

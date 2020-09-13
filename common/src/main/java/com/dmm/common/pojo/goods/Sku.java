package com.dmm.common.pojo.goods;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Map;

public class Sku  implements Serializable {
    private Long id;
    private Long spuId;
    //12位产品编码
    private String productCode;
    private String title;
    private Long albumId;
    private String categoryName;
    private BigDecimal price;
    private Integer volume;//库存
    private Integer status;
    private Map<String,Spec> specMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSpuId() {
        return spuId;
    }

    public void setSpuId(Long spuId) {
        this.spuId = spuId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Spec> getSpecMap() {
        return specMap;
    }

    public void setSpecMap(Map<String, Spec> specMap) {
        this.specMap = specMap;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", spuId=" + spuId +
                ", productCode='" + productCode + '\'' +
                ", title='" + title + '\'' +
                ", albumId=" + albumId +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", status=" + status +
                ", specMap=" + specMap +
                '}';
    }
}

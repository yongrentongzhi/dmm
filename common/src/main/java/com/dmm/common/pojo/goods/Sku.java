package com.dmm.common.pojo.goods;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;
import java.util.Map;
@Document(indexName = "sku")
public class Sku  implements Serializable {
    @Id
    private Long id;
    private Long spuId;
    //12位产品编码
    private String productCode;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;
    private Long albumId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    @Field(type = FieldType.Keyword)
    private String categoryName;
    @Field(type = FieldType.Double)
    private BigDecimal price;
    private Integer volume;//库存
    private Integer status;
    @Field(type = FieldType.Keyword,index = false)
    private String album;
    @Field(type = FieldType.Keyword,index = false)
    private List<AlbumItem> albumItemList;
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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<AlbumItem> getAlbumItemList() {
        return albumItemList;
    }

    public void setAlbumItemList(List<AlbumItem> albumItemList) {
        this.albumItemList = albumItemList;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", spuId=" + spuId +
                ", productCode='" + productCode + '\'' +
                ", title='" + title + '\'' +
                ", albumId=" + albumId +
                ", brandName='" + brandName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", status=" + status +
                ", album='" + album + '\'' +
                ", albumItemList=" + albumItemList +
                ", specMap=" + specMap +
                '}';
    }
}

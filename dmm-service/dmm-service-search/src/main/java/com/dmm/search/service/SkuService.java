package com.dmm.search.service;

import java.util.Map;


public interface SkuService {

    /**
     * //1.调用 goods微服务的fegin 查询 符合条件的sku的数据
       //2.调用spring data elasticsearch的API 导入到ES中
     */
    void  importEs();


    /**
     *
     * @param searchMap
     * @return
     */
    Map search(Map<String,String> searchMap);
}

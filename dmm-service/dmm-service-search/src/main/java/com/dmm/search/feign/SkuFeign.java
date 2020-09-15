package com.dmm.search.feign;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;

import com.dmm.common.pojo.goods.Sku;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("goods")
public interface SkuFeign {
    @PostMapping
    public Result save(@RequestBody Sku sku) ;


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) ;

    @PutMapping
    public Result update(@RequestBody Sku sku) ;

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) ;


    @GetMapping("/search/{spuId}")
    public Result findPageBySpuIdId(@PathVariable("spuId") Long spuId) ;



    @PostMapping("/search/{skuName}/{pageNum}/{size}")
    public Result<Page<Sku>> findPage(@PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("size") Integer size,
                                      @PathVariable("skuName") String skuName) ;


    @GetMapping("/search/{status}/{pageNum}/{size}")
    public Result<Page<Sku>> findPageByStatus(@PathVariable("pageNum") Integer pageNum,
                                              @PathVariable("size") Integer size,
                                              @PathVariable("status") Integer status) ;
    @PostMapping("/search")
    public Result<List<Sku>> findByStatusAndSpuId(Long spuId,Integer status);
}

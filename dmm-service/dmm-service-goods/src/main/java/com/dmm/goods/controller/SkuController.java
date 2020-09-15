package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.Sku;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.service.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods/sku", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkuController {
    @Autowired
    private ISkuService skuService;
    @Autowired
    private UidGenerator uidGenerator;

    @PostMapping
    public Result save(@RequestBody Sku sku) {
        sku.setId(uidGenerator.getUID());
        skuService.save(sku);
        return new Result(StatusCode.OK, true, "保存Sku成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        skuService.removeById(id);

        return new Result(StatusCode.OK, true, "删除Sku成功");
    }

    @PutMapping
    public Result update(@RequestBody Sku sku) {
        skuService.updateById(sku);
        return new Result(StatusCode.OK, true, "修改Sku成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        Sku sku = skuService.getById(id);
        return new Result(StatusCode.OK, true, "查询Sku成功", sku);
    }

    @GetMapping("/search/{spuId}")
    public Result findPageBySpuIdId(@PathVariable("spuId") Long spuId) {
        QueryWrapper<Sku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id", spuId);
        List<Object> skuList = skuService.listObjs(queryWrapper);
        return new Result(StatusCode.OK, true, "按spuId搜索Sku列表", skuList);
    }


    @GetMapping("/search/{skuName}/{pageNum}/{size}")
    public Result<Page<Sku>> findPageByName(@PathVariable("pageNum") Integer pageNum,
                                            @PathVariable("size") Integer size,
                                            @PathVariable("skuName") String skuName) {
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(skuName)) {
            wrapper.likeRight("title", skuName);
        }
        Page<Sku> page = new Page<>(pageNum, size);
        Page<Sku> skuPage = skuService.page(page, wrapper);

        return new Result<Page<Sku>>(StatusCode.OK, true, "按名称搜索Sku列表", skuPage);
    }

    @GetMapping("/search/{status}/{pageNum}/{size}")
    public Result<Page<Sku>> findPageByStatus(@PathVariable("pageNum") Integer pageNum,
                                              @PathVariable("size") Integer size,
                                              @PathVariable("status") Long status) {
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(status)) {
            wrapper.likeRight("status", status);
        }
        Page<Sku> page = new Page<>(pageNum, size);
        Page<Sku> skuPage = skuService.page(page, wrapper);

        return new Result<Page<Sku>>(StatusCode.OK, true, "按状态搜索Sku列表", skuPage);
    }

    @PostMapping("/search")
    public Result<List<Sku>> findByStatusAndSpuId(Long spuId,Integer status) {
        QueryWrapper<Sku> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(spuId)) {
            wrapper.eq("spu_id",spuId);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status", status);
        }
       List<Sku> skuList=skuService.list(wrapper);


        return new Result<>(StatusCode.OK, true, "按状态搜索Sku列表", skuList);
    }
}

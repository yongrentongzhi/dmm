package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.Brand;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.service.IBrandService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandController {
    @Autowired
    private IBrandService brandService;
    @Autowired
    private UidGenerator uidGenerator;

    @PostMapping
    public Result save(@RequestBody Brand brand) {
        brand.setId(uidGenerator.getUID());
        brandService.save(brand);
        return new Result(StatusCode.OK, true, "保存品牌成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        brandService.removeById(id);

        return new Result(StatusCode.OK, true, "删除品牌成功");
    }

    @PutMapping
    public Result update(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return new Result(StatusCode.OK, true, "修改品牌成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        Brand brand = brandService.getById(id);
        return new Result(StatusCode.OK, true, "查询品牌成功", brand);
    }

    @GetMapping("/search/{categoryId}")
    public Result findPageByCategoryId(@PathVariable("categoryId") Long categoryId) {
    QueryWrapper<Brand> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("category_id",categoryId);
    List<Object> brandList = brandService.listObjs(queryWrapper);

        return new Result(StatusCode.OK, true, "按名称搜索品牌列表", brandList);
    }


    @PostMapping("/search/{brandName}/{pageNum}/{size}")
    public Result<Page<Brand>> findPage(@PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("size") Integer size,
                                        @PathVariable("brandName") String brandName) {
        QueryWrapper<Brand> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(brandName)) {
            wrapper.likeRight("name", brandName);
        }
        Page<Brand> page = new Page<>(pageNum, size);
        Page<Brand> brandPage = brandService.page(page, wrapper);

        return new Result<Page<Brand>>(StatusCode.OK, true, "按名称搜索品牌列表", brandPage);
    }
}

package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.Spu;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.service.ISpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods/spu", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpuController {
    @Autowired
    private ISpuService spuService;
    @Autowired
    private UidGenerator uidGenerator;

    @PostMapping
    public Result save(@RequestBody Spu spu) {
        spu.setId(uidGenerator.getUID());
        spuService.save(spu);
        return new Result(StatusCode.OK, true, "保存Spu成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        spuService.removeById(id);

        return new Result(StatusCode.OK, true, "删除Spu成功");
    }

    @PutMapping
    public Result update(@RequestBody Spu spu) {
        spuService.updateById(spu);
        return new Result(StatusCode.OK, true, "修改Spu成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        Spu spu = spuService.getById(id);
        return new Result(StatusCode.OK, true, "查询Spu成功", spu);
    }

    @GetMapping("/search/{categoryId}")
    public Result findPageByCategoryId(@PathVariable("categoryId") Long categoryId) {
    QueryWrapper<Spu> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("category_id2",categoryId);
    List<Object> spuList = spuService.listObjs(queryWrapper);

        return new Result(StatusCode.OK, true, "按2级分类搜索Spu列表", spuList);
    }


    @PostMapping("/search/{spuName}/{pageNum}/{size}")
    public Result<Page<Spu>> findPage(@PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("size") Integer size,
                                        @PathVariable("spuName") String spuName) {
        QueryWrapper<Spu> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(spuName)) {
            wrapper.likeRight("title", spuName);
        }
        Page<Spu> page = new Page<>(pageNum, size);
        Page<Spu> spuPage = spuService.page(page, wrapper);

        return new Result<Page<Spu>>(StatusCode.OK, true, "按名称搜索Spu列表", spuPage);
    }
}

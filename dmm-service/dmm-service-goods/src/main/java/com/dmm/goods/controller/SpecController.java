package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.Spec;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.service.ISpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods/spec", produces = MediaType.APPLICATION_JSON_VALUE)
public class SpecController {
    @Autowired
    private ISpecService specService;
    @Autowired
    private UidGenerator uidGenerator;

    @PostMapping
    public Result save(@RequestBody Spec spec) {
        spec.setId(uidGenerator.getUID());
        specService.save(spec);
        return new Result(StatusCode.OK, true, "保存规格成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        specService.removeById(id);

        return new Result(StatusCode.OK, true, "删除规格成功");
    }

    @PutMapping
    public Result update(@RequestBody Spec spec) {
        specService.updateById(spec);
        return new Result(StatusCode.OK, true, "修改规格成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        Spec spec = specService.getById(id);
        return new Result(StatusCode.OK, true, "查询规格成功", spec);
    }

    @GetMapping("/search/{spuId}")
    public Result findPageByCategoryId(@PathVariable("spuId") Long spuId) {
    QueryWrapper<Spec> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("spu_id",spuId);
    List<Object> specList = specService.listObjs(queryWrapper);

        return new Result(StatusCode.OK, true, "按spuId搜索规格列表", specList);
    }


    @PostMapping("/search/{specName}/{pageNum}/{size}")
    public Result<Page<Spec>> findPage(@PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("size") Integer size,
                                        @PathVariable("specName") String specName) {
        QueryWrapper<Spec> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(specName)) {
            wrapper.likeRight("name", specName);
        }
        Page<Spec> page = new Page<>(pageNum, size);
        Page<Spec> specPage = specService.page(page, wrapper);

        return new Result<Page<Spec>>(StatusCode.OK, true, "按名称搜索规格列表", specPage);
    }
}

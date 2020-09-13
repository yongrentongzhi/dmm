package com.dmm.goods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dmm.common.pojo.Result;
import com.dmm.common.pojo.StatusCode;
import com.dmm.common.pojo.goods.Category;
import com.dmm.common.uid.UidGenerator;
import com.dmm.goods.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods/category", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private UidGenerator uidGenerator;

    @PostMapping
    public Result save(@RequestBody Category category) {
        category.setId(uidGenerator.getUID());
        categoryService.save(category);
        return new Result(StatusCode.OK, true, "保存分类成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        categoryService.removeById(id);

        return new Result(StatusCode.OK, true, "删除分类成功");
    }

    @PutMapping
    public Result update(@RequestBody Category category) {
        categoryService.updateById(category);
        return new Result(StatusCode.OK, true, "修改分类成功");
    }

    @GetMapping("/id")
    public Result findById(@PathVariable Long id) {
        Category category = categoryService.getById(id);
        return new Result(StatusCode.OK, true, "查询分类成功", category);
    }

    @GetMapping("/search/{parentId}")
    public Result findPageByCategoryId(@PathVariable("parentId") Long parentId) {
    QueryWrapper<Category> queryWrapper=new QueryWrapper<>();
    queryWrapper.eq("parent_id",parentId);
    List<Object> categoryList = categoryService.listObjs(queryWrapper);

        return new Result(StatusCode.OK, true, "按父级分类搜索分类列表", categoryList);
    }


    @PostMapping("/search/{categoryName}/{pageNum}/{size}")
    public Result<Page<Category>> findPage(@PathVariable("pageNum") Integer pageNum,
                                        @PathVariable("size") Integer size,
                                        @PathVariable("categoryName") String categoryName) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(categoryName)) {
            wrapper.likeRight("name", categoryName);
        }
        Page<Category> page = new Page<>(pageNum, size);
        Page<Category> categoryPage = categoryService.page(page, wrapper);

        return new Result<Page<Category>>(StatusCode.OK, true, "按名称搜索分类列表", categoryPage);
    }
}

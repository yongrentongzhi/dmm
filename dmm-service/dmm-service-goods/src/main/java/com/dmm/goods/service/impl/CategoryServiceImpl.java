package com.dmm.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmm.common.pojo.goods.Category;
import com.dmm.goods.dao.CategoryMapper;
import com.dmm.goods.service.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
}

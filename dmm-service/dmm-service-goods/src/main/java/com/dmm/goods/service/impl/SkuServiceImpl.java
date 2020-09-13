package com.dmm.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmm.common.pojo.goods.Sku;
import com.dmm.goods.dao.SkuMapper;
import com.dmm.goods.service.ISkuService;
import org.springframework.stereotype.Service;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements ISkuService {
}

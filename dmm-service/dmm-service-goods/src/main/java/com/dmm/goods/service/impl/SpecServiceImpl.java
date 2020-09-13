package com.dmm.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmm.common.pojo.goods.Spec;
import com.dmm.goods.dao.SpecMapper;
import com.dmm.goods.service.ISpecService;
import org.springframework.stereotype.Service;

@Service
public class SpecServiceImpl extends ServiceImpl<SpecMapper, Spec> implements ISpecService {
}

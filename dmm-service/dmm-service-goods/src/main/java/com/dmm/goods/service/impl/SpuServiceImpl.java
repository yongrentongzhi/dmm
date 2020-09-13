package com.dmm.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmm.common.pojo.goods.Spu;
import com.dmm.goods.dao.SpuMapper;
import com.dmm.goods.service.ISpuService;
import org.springframework.stereotype.Service;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements ISpuService {
}

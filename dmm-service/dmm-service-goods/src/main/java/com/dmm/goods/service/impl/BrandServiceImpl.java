package com.dmm.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dmm.common.pojo.goods.Album;
import com.dmm.common.pojo.goods.Brand;
import com.dmm.goods.dao.AlbumMapper;
import com.dmm.goods.dao.BrandMapper;
import com.dmm.goods.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper,Brand > implements IBrandService {



}

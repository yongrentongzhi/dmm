package com.dmm.goods;

import com.dmm.common.pojo.goods.Brand;
import com.dmm.goods.dao.BrandMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@RunWith(SpringRunner.class)
@SpringBootTest
public class brandMapperTest {

    @Autowired
    private BrandMapper brandMapper;

    //    int insertOne(Brand brand);

    }
//    int insertList(List<Brand> brandList);
//
//    int deleteOneById(Integer id);
//
//    int deleteListById(List<Integer> idList);
//
//    int updateById(Brand brand);
//
//
//    Brand selectById(Integer id);
//
//    List<Brand> selectByIdList(List<Integer> idList);
//
//    List<Brand> selectByCriteria(Brand brand);


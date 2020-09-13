package com.dmm.goods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dmm.common.pojo.goods.Album;
import com.dmm.common.pojo.goods.AlbumItem;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumItemMapper extends BaseMapper<AlbumItem> {
}

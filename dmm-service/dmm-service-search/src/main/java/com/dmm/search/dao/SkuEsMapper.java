package com.dmm.search.dao;

import com.dmm.common.pojo.goods.Sku;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.search.dao *
 * @since 1.0
 */
public interface SkuEsMapper extends ElasticsearchRepository<Sku,Long> {
}

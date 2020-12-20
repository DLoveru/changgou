package com.changgou.goods.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 */
public interface BrandMapper extends BaseMapper<Brand> {

    /**
     * 根据id查询分类品牌数据
     * @param id
     * @return
     */
    @Select("select tb.id, tb.name from tb_brand tb, tb_category_brand cb\n" +
            "where tb.id = cb.brand_id and category_id = #{id}")
    List<Brand> findByGateGoryId(Integer id);
}

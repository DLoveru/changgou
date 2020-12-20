package com.changgou.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;
import java.util.Objects;

/**
 *
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired(required = false)
    private BrandMapper brandMapper;


    /**
     * 根据分类id查询查询分类品牌数据
     * @param id
     * @return
     */
    @Override
    public List<Brand> findByGateGoryId(Integer id) {
        List<Brand> brandList = brandMapper.findByGateGoryId(id);
        return brandList;
    }

    /**
     * Brand条件+分页查询
     * @param brand 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        QueryWrapper example = createQueryWrapper(brand);
        //执行搜索
        return new PageInfo<Brand>(brandMapper.selectList(example));
    }

    /**
     * Brand分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Brand>(brandMapper.selectList(null));
    }

    /**
     * Brand条件查询
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand){
        //构建查询条件
       QueryWrapper example = createQueryWrapper(brand);
        //根据构建的条件查询数据
        return brandMapper.selectList(example);
    }


    /**
     * Brand构建查询对象
     * @param brand
     * @return
     */
    public QueryWrapper createQueryWrapper(Brand brand){
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if(Objects.isNull(brand)){
            return queryWrapper;
        }
            // 品牌id
            if(!StringUtils.isEmpty(brand.getId())){
                queryWrapper.eq("id",brand.getId());
            }
            // 品牌名称
            if(!StringUtils.isEmpty(brand.getName())){
                queryWrapper.like("name","%"+brand.getName()+"%");
            }
            // 品牌图片地址
            if(!StringUtils.isEmpty(brand.getImage())){
                queryWrapper.eq("image",brand.getImage());
            }
            // 品牌的首字母
            if(!StringUtils.isEmpty(brand.getLetter())){
                queryWrapper.eq("letter",brand.getLetter());
            }
            // 排序
            if(!StringUtils.isEmpty(brand.getSeq())){
                queryWrapper.eq("seq",brand.getSeq());
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        brandMapper.deleteById(id);
    }

    /**
     * 修改Brand
     * @param brand
     */
    @Override
    public void update(Brand brand){
        brandMapper.updateById(brand);
    }

    /**
     * 增加Brand
     * @param brand
     */
    @Override
    public void add(Brand brand){
        brandMapper.insert(brand);
    }

    /**
     * 根据ID查询Brand
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id){
        return  brandMapper.selectById(id);
    }

    /**
     * 查询Brand全部数据
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectList(null);
    }
}

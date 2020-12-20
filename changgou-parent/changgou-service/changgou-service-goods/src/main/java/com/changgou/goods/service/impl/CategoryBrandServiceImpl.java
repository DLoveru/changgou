package com.changgou.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changgou.goods.dao.CategoryBrandMapper;
import com.changgou.goods.pojo.CategoryBrand;
import com.changgou.goods.service.CategoryBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;

/****
 * @Author:传智播客
 * @Description:CategoryBrand业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {

    @Autowired
    private CategoryBrandMapper categoryBrandMapper;


    /**
     * CategoryBrand条件+分页查询
     *
     * @param categoryBrand 查询条件
     * @param page          页码
     * @param size          页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<CategoryBrand> findPage(CategoryBrand categoryBrand, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        QueryWrapper example = createQueryWrapper(categoryBrand);
        //执行搜索
        return new PageInfo<CategoryBrand>(categoryBrandMapper.selectList(example));
    }

    /**
     * CategoryBrand分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<CategoryBrand> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<CategoryBrand>(categoryBrandMapper.selectList(null));
    }

    /**
     * CategoryBrand条件查询
     *
     * @param categoryBrand
     * @return
     */
    @Override
    public List<CategoryBrand> findList(CategoryBrand categoryBrand) {
        //构建查询条件
        QueryWrapper example = createQueryWrapper(categoryBrand);
        //根据构建的条件查询数据
        return categoryBrandMapper.selectList(example);
    }


    /**
     * CategoryBrand构建查询对象
     *
     * @param categoryBrand
     * @return
     */
    public QueryWrapper createQueryWrapper(CategoryBrand categoryBrand) {
        QueryWrapper example = new QueryWrapper();
        if (categoryBrand != null) {
            // 分类ID
            if (!StringUtils.isEmpty(categoryBrand.getCategoryId())) {
                example.eq("categoryId", categoryBrand.getCategoryId());
            }
            // 品牌ID
            if (!StringUtils.isEmpty(categoryBrand.getBrandId())) {
                example.eq("brandId", categoryBrand.getBrandId());
            }
        }
        return example;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        categoryBrandMapper.deleteById(id);
    }

    /**
     * 修改CategoryBrand
     *
     * @param categoryBrand
     */
    @Override
    public void update(CategoryBrand categoryBrand) {
        categoryBrandMapper.updateById(categoryBrand);
    }

    /**
     * 增加CategoryBrand
     *
     * @param categoryBrand
     */
    @Override
    public void add(CategoryBrand categoryBrand) {
        categoryBrandMapper.insert(categoryBrand);
    }

    /**
     * 根据ID查询CategoryBrand
     *
     * @param id
     * @return
     */
    @Override
    public CategoryBrand findById(Integer id) {
        return categoryBrandMapper.selectById(id);
    }

    /**
     * 查询CategoryBrand全部数据
     *
     * @return
     */
    @Override
    public List<CategoryBrand> findAll() {
        return categoryBrandMapper.selectList(null);
    }
}

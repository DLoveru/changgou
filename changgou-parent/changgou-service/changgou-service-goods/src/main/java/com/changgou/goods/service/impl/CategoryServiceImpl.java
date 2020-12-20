package com.changgou.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changgou.goods.dao.*;
import com.changgou.goods.pojo.*;
import com.changgou.goods.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslBindingsFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/****
 * @Author:传智播客
 * @Description:Category业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @Autowired(required = false)
    private BrandMapper brandMapper;

    @Autowired(required = false)
    private TemplateMapper templateMapper;

    @Autowired(required = false)
    private SpecMapper specMapper;

    @Autowired(required = false)
    private ParaMapper paraMapper;


    /**
     * 一次请求返回所有数据
     * @param categoryId
     * @return
     */
    @Override
    public Map<String, Object> getObjectAll(Integer categoryId) {
        //创建map封装返回的数据
        HashMap<String, Object> map = new HashMap<>();
        //获得商品的品牌信息
        List<Brand> brands = brandMapper.findByGateGoryId(categoryId);
        //获得模板查询
        Category category = categoryMapper.selectById(categoryId);
        Template template = templateMapper.selectById(category.getTemplateId());
        //获得商品的属性
        QueryWrapper skuWrapper = new QueryWrapper();
        skuWrapper.eq("template_id",category.getTemplateId());
        List<Spec> specList = specMapper.selectList(skuWrapper);
        //获得商品的参数列表查询
        QueryWrapper paraWrapper = new QueryWrapper();
        paraWrapper.eq("template_id",category.getTemplateId());
        List<Para> paraList = paraMapper.selectList(paraWrapper);
        //封装数据
        map.put("brands",brands);
        map.put("specList",specList);
        map.put("paraList",paraList);
        return map;
    }

    /**
     * 获得商品分类
     * @param parentId
     * @return
     */
    @Override
    public List<Category> findByParentId(Integer parentId) {
        QueryWrapper paraWrapper = new QueryWrapper();
        paraWrapper.eq("parent_id",parentId);
        return categoryMapper.selectList(paraWrapper);
    }

    /**
     * Category条件+分页查询
     * @param category 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Category> findPage(Category category, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
       QueryWrapper example = createQueryWrapper(category);
        //执行搜索
        return new PageInfo<Category>(categoryMapper.selectList(example));
    }

    /**
     * Category分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Category> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Category>(categoryMapper.selectList(null));
    }

    /**
     * Category条件查询
     * @param category
     * @return
     */
    @Override
    public List<Category> findList(Category category){
        //构建查询条件
       QueryWrapper example = createQueryWrapper(category);
        //根据构建的条件查询数据
        return categoryMapper.selectList(example);
    }


    /**
     * Category构建查询对象
     * @param category
     * @return
     */
    public QueryWrapper createQueryWrapper(Category category){
       QueryWrapper example = new QueryWrapper();
        if(category!=null){
            // 分类ID
            if(!StringUtils.isEmpty(category.getId())){
                    example.eq("id",category.getId());
            }
            // 分类名称
            if(!StringUtils.isEmpty(category.getName())){
                    example.like("name","%"+category.getName()+"%");
            }
            // 商品数量
            if(!StringUtils.isEmpty(category.getGoodsNum())){
                    example.eq("goods_num",category.getGoodsNum());
            }
            // 是否显示
            if(!StringUtils.isEmpty(category.getIsShow())){
                    example.eq("is_show",category.getIsShow());
            }
            // 是否导航
            if(!StringUtils.isEmpty(category.getIsMenu())){
                    example.eq("is_menu",category.getIsMenu());
            }
            // 排序
            if(!StringUtils.isEmpty(category.getSeq())){
                    example.eq("seq",category.getSeq());
            }
            // 上级ID
            if(!StringUtils.isEmpty(category.getParentId())){
                    example.eq("parent_id",category.getParentId());
            }
            // 模板ID
            if(!StringUtils.isEmpty(category.getTemplateId())){
                    example.eq("template_id",category.getTemplateId());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        categoryMapper.deleteById(id);
    }

    /**
     * 修改Category
     * @param category
     */
    @Override
    public void update(Category category){
        categoryMapper.updateById(category);
    }

    /**
     * 增加Category
     * @param category
     */
    @Override
    public void add(Category category){
        categoryMapper.insert(category);
    }

    /**
     * 根据ID查询Category
     * @param id
     * @return
     */
    @Override
    public Category findById(Integer id){
        return  categoryMapper.selectById(id);
    }

    /**
     * 查询Category全部数据
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectList(null);
    }

}

package com.changgou.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changgou.goods.dao.CategoryMapper;
import com.changgou.goods.dao.ParaMapper;
import com.changgou.goods.pojo.Category;
import com.changgou.goods.pojo.Para;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.ParaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;

/****
 * @Author:传智播客
 * @Description:Para业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class ParaServiceImpl implements ParaService {

    @Autowired(required = false)
    private ParaMapper paraMapper;

    @Autowired(required = false)
    private CategoryMapper categoryMapper;


    /**
     * 根据模板id查询参数列表查询
     * @param categoryId
     * @return
     */
    @Override
    public List<Para> findParaByCategoryId(Integer categoryId) {
        //先根据categoryId查询到category对象，在或的templeId
        Category category = categoryMapper.selectById(categoryId);
        //然后根据templeId获得Para对象
        QueryWrapper skuWrapper = new QueryWrapper();
        skuWrapper.eq("template_id",category.getTemplateId());
        List<Para> paraList = paraMapper.selectList(skuWrapper);
        return paraList;
    }

    /**
     * Para条件+分页查询
     * @param para 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Para> findPage(Para para, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
       QueryWrapper example = createQueryWrapper(para);
        //执行搜索
        return new PageInfo<Para>(paraMapper.selectList(example));
    }

    /**
     * Para分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Para> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Para>(paraMapper.selectList(null));
    }

    /**
     * Para条件查询
     * @param para
     * @return
     */
    @Override
    public List<Para> findList(Para para){
        //构建查询条件
       QueryWrapper example = createQueryWrapper(para);
        //根据构建的条件查询数据
        return paraMapper.selectList(example);
    }


    /**
     * Para构建查询对象
     * @param para
     * @return
     */
    public QueryWrapper createQueryWrapper(Para para){
       QueryWrapper example = new QueryWrapper();
        if(para!=null){
            // id
            if(!StringUtils.isEmpty(para.getId())){
                    example.eq("id",para.getId());
            }
            // 名称
            if(!StringUtils.isEmpty(para.getName())){
                    example.like("name","%"+para.getName()+"%");
            }
            // 选项
            if(!StringUtils.isEmpty(para.getOptions())){
                    example.eq("options",para.getOptions());
            }
            // 排序
            if(!StringUtils.isEmpty(para.getSeq())){
                    example.eq("seq",para.getSeq());
            }
            // 模板ID
            if(!StringUtils.isEmpty(para.getTemplateId())){
                    example.eq("template_id",para.getTemplateId());
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
        paraMapper.deleteById(id);
    }

    /**
     * 修改Para
     * @param para
     */
    @Override
    public void update(Para para){
        paraMapper.updateById(para);
    }

    /**
     * 增加Para
     * @param para
     */
    @Override
    public void add(Para para){
        paraMapper.insert(para);
    }

    /**
     * 根据ID查询Para
     * @param id
     * @return
     */
    @Override
    public Para findById(Integer id){
        return  paraMapper.selectById(id);
    }

    /**
     * 查询Para全部数据
     * @return
     */
    @Override
    public List<Para> findAll() {
        return paraMapper.selectList(null);
    }
}

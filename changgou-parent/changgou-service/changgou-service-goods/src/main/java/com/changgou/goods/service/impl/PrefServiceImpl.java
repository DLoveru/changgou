package com.changgou.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changgou.goods.dao.PrefMapper;
import com.changgou.goods.pojo.Pref;
import com.changgou.goods.service.PrefService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import java.util.List;

/****
 * @Author:传智播客
 * @Description:Pref业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class PrefServiceImpl implements PrefService {

    @Autowired
    private PrefMapper prefMapper;


    /**
     * Pref条件+分页查询
     * @param pref 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Pref> findPage(Pref pref, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
       QueryWrapper example = createQueryWrapper(pref);
        //执行搜索
        return new PageInfo<Pref>(prefMapper.selectList(example));
    }

    /**
     * Pref分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Pref> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Pref>(prefMapper.selectList(null));
    }

    /**
     * Pref条件查询
     * @param pref
     * @return
     */
    @Override
    public List<Pref> findList(Pref pref){
        //构建查询条件
       QueryWrapper example = createQueryWrapper(pref);
        //根据构建的条件查询数据
        return prefMapper.selectList(example);
    }


    /**
     * Pref构建查询对象
     * @param pref
     * @return
     */
    public QueryWrapper createQueryWrapper(Pref pref){
       QueryWrapper example = new QueryWrapper();
        if(pref!=null){
            // ID
            if(!StringUtils.isEmpty(pref.getId())){
                    example.eq("id",pref.getId());
            }
            // 分类ID
            if(!StringUtils.isEmpty(pref.getCateId())){
                    example.eq("cate_id",pref.getCateId());
            }
            // 消费金额
            if(!StringUtils.isEmpty(pref.getBuyMoney())){
                    example.eq("buy_money",pref.getBuyMoney());
            }
            // 优惠金额
            if(!StringUtils.isEmpty(pref.getPreMoney())){
                    example.eq("pre_money",pref.getPreMoney());
            }
            // 活动开始日期
            if(!StringUtils.isEmpty(pref.getStartTime())){
                    example.eq("start_time",pref.getStartTime());
            }
            // 活动截至日期
            if(!StringUtils.isEmpty(pref.getEndTime())){
                    example.eq("end_time",pref.getEndTime());
            }
            // 类型,1:普通订单，2：限时活动
            if(!StringUtils.isEmpty(pref.getType())){
                    example.eq("type",pref.getType());
            }
            // 状态,1:有效，0：无效
            if(!StringUtils.isEmpty(pref.getState())){
                    example.eq("state",pref.getState());
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
        prefMapper.deleteById(id);
    }

    /**
     * 修改Pref
     * @param pref
     */
    @Override
    public void update(Pref pref){
        prefMapper.updateById(pref);
    }

    /**
     * 增加Pref
     * @param pref
     */
    @Override
    public void add(Pref pref){
        prefMapper.insert(pref);
    }

    /**
     * 根据ID查询Pref
     * @param id
     * @return
     */
    @Override
    public Pref findById(Integer id){
        return  prefMapper.selectById(id);
    }

    /**
     * 查询Pref全部数据
     * @return
     */
    @Override
    public List<Pref> findAll() {
        return prefMapper.selectList(null);
    }
}

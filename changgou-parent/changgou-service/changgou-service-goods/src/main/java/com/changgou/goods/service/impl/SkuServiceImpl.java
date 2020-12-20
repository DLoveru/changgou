//package com.changgou.goods.service.impl;
//
//import com.changgou.goods.dao.SkuMapper;
//import com.changgou.goods.pojo.Sku;
//import com.changgou.goods.service.SkuService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//
//import java.util.List;
//
///****
// * @Author:传智播客
// * @Description:Sku业务层接口实现类
// * @Date 2019/6/14 0:16
// *****/
//@Service
//public class SkuServiceImpl implements SkuService {
//
//    @Autowired(required = false)
//    private SkuMapper skuMapper;
//
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    /**
//     * 扣减库存
//     * @param username
//     */
//    @Override
//    public void decrCount(String username) {
//        // 获取购物车数据
//        List<OrderItem> orderItems = redisTemplate.boundHashOps("cart_" + username).values();
//        if (orderItems != null && orderItems.size() > 0) {
//            for (OrderItem orderItem : orderItems) {
//                // 扣减库存,更新库存表
//                // update tb_sku set num = num - 20 where id = skuId and num > 20  // 防止超卖
//                int count = skuMapper.decrCount(orderItem.getSkuId(), orderItem.getNum());
//                if (count <= 0){
//                    throw new RuntimeException("商品库存不足，请减少购买数量！！！");
//                }
//            }
//        }
//    }
//
//    /**
//     * 查询库存为正常状态下的列表信息
//     * @param status
//     * @return
//     */
//    @Override
//    public List<Sku> findSkusByStatus(String status) {
//        Sku sku = new Sku();
//        sku.setStatus(status);
//        List<Sku> list = skuMapper.select(sku);
//        return list;
//    }
//
//    /**
//     * Sku条件+分页查询
//     * @param sku 查询条件
//     * @param page 页码
//     * @param size 页大小
//     * @return 分页结果
//     */
//    @Override
//    public PageInfo<Sku> findPage(Sku sku, int page, int size){
//        //分页
//        PageHelper.startPage(page,size);
//        //搜索条件构建
//       QueryWrapper example = createQueryWrapper(sku);
//        //执行搜索
//        return new PageInfo<Sku>(skuMapper.selectList(example));
//    }
//
//    /**
//     * Sku分页查询
//     * @param page
//     * @param size
//     * @return
//     */
//    @Override
//    public PageInfo<Sku> findPage(int page, int size){
//        //静态分页
//        PageHelper.startPage(page,size);
//        //分页查询
//        return new PageInfo<Sku>(skuMapper.selectList(null));
//    }
//
//    /**
//     * Sku条件查询
//     * @param sku
//     * @return
//     */
//    @Override
//    public List<Sku> findList(Sku sku){
//        //构建查询条件
//       QueryWrapper example = createQueryWrapper(sku);
//        //根据构建的条件查询数据
//        return skuMapper.selectList(example);
//    }
//
//
//    /**
//     * Sku构建查询对象
//     * @param sku
//     * @return
//     */
//    public QueryWrapper createQueryWrapper(Sku sku){
//       QueryWrapper example=new Example(Sku.class);
//       QueryWrapper example = new QueryWrapper();
//        if(sku!=null){
//            // 商品id
//            if(!StringUtils.isEmpty(sku.getId())){
//                    example.eq("id",sku.getId());
//            }
//            // 商品条码
//            if(!StringUtils.isEmpty(sku.getSn())){
//                    example.eq("sn",sku.getSn());
//            }
//            // SKU名称
//            if(!StringUtils.isEmpty(sku.getName())){
//                    example.like("name","%"+sku.getName()+"%");
//            }
//            // 价格（分）
//            if(!StringUtils.isEmpty(sku.getPrice())){
//                    example.eq("price",sku.getPrice());
//            }
//            // 库存数量
//            if(!StringUtils.isEmpty(sku.getNum())){
//                    example.eq("num",sku.getNum());
//            }
//            // 库存预警数量
//            if(!StringUtils.isEmpty(sku.getAlertNum())){
//                    example.eq("alertNum",sku.getAlertNum());
//            }
//            // 商品图片
//            if(!StringUtils.isEmpty(sku.getImage())){
//                    example.eq("image",sku.getImage());
//            }
//            // 商品图片列表
//            if(!StringUtils.isEmpty(sku.getImages())){
//                    example.eq("images",sku.getImages());
//            }
//            // 重量（克）
//            if(!StringUtils.isEmpty(sku.getWeight())){
//                    example.eq("weight",sku.getWeight());
//            }
//            // 创建时间
//            if(!StringUtils.isEmpty(sku.getCreateTime())){
//                    example.eq("createTime",sku.getCreateTime());
//            }
//            // 更新时间
//            if(!StringUtils.isEmpty(sku.getUpdateTime())){
//                    example.eq("updateTime",sku.getUpdateTime());
//            }
//            // SPUID
//            if(!StringUtils.isEmpty(sku.getSpuId())){
//                    example.eq("spuId",sku.getSpuId());
//            }
//            // 类目ID
//            if(!StringUtils.isEmpty(sku.getCategoryId())){
//                    example.eq("categoryId",sku.getCategoryId());
//            }
//            // 类目名称
//            if(!StringUtils.isEmpty(sku.getCategoryName())){
//                    example.eq("categoryName",sku.getCategoryName());
//            }
//            // 品牌名称
//            if(!StringUtils.isEmpty(sku.getBrandName())){
//                    example.eq("brandName",sku.getBrandName());
//            }
//            // 规格
//            if(!StringUtils.isEmpty(sku.getSpec())){
//                    example.eq("spec",sku.getSpec());
//            }
//            // 销量
//            if(!StringUtils.isEmpty(sku.getSaleNum())){
//                    example.eq("saleNum",sku.getSaleNum());
//            }
//            // 评论数
//            if(!StringUtils.isEmpty(sku.getCommentNum())){
//                    example.eq("commentNum",sku.getCommentNum());
//            }
//            // 商品状态 1-正常，2-下架，3-删除
//            if(!StringUtils.isEmpty(sku.getStatus())){
//                    example.eq("status",sku.getStatus());
//            }
//        }
//        return example;
//    }
//
//    /**
//     * 删除
//     * @param id
//     */
//    @Override
//    public void delete(Long id){
//        skuMapper.deleteById(id);
//    }
//
//    /**
//     * 修改Sku
//     * @param sku
//     */
//    @Override
//    public void update(Sku sku){
//        skuMapper.updateById(sku);
//    }
//
//    /**
//     * 增加Sku
//     * @param sku
//     */
//    @Override
//    public void add(Sku sku){
//        skuMapper.insert(sku);
//    }
//
//    /**
//     * 根据ID查询Sku
//     * @param id
//     * @return
//     */
//    @Override
//    public Sku findById(Long id){
//        return  skuMapper.selectById(id);
//    }
//
//    /**
//     * 查询Sku全部数据
//     * @return
//     */
//    @Override
//    public List<Sku> findAll() {
//        return skuMapper.selectList(null);
//    }
//}

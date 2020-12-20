package com.changgou.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.changgou.entity.EmptyUtil;
import com.changgou.goods.dao.AlbumMapper;
import com.changgou.goods.pojo.Album;
import com.changgou.goods.service.AlbumService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/****
 * @Author:传智播客
 * @Description:Album业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired(required = false)
    private AlbumMapper albumMapper;


    /**
     * Album条件+分页查询
     *
     * @param album 查询条件
     * @param page  页码
     * @param size  页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Album> findPage(Album album, int page, int size) {
        //分页
        PageHelper.startPage(page, size);
        //搜索条件构建
        QueryWrapper queryWrapper = createQueryWrapper(album);
        //执行搜索
        return new PageInfo<Album>(albumMapper.selectList(queryWrapper));
    }

    /**
     * Album分页查询
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Album> findPage(int page, int size) {
        //静态分页
        PageHelper.startPage(page, size);
        //分页查询
        return new PageInfo<Album>(albumMapper.selectList(null));
    }

    /**
     * Album条件查询
     *
     * @param album
     * @return
     */
    @Override
    public List<Album> findList(Album album) {
        //构建查询条件
        QueryWrapper example = createQueryWrapper(album);
        //根据构建的条件查询数据
        return albumMapper.selectList(example);
    }


    /**
     * Album构建查询对象
     *
     * @param album
     * @return
     */
    public QueryWrapper createQueryWrapper(Album album) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (Objects.isNull(album)) {
            return queryWrapper;
        }
        if (!StringUtils.isEmpty(album.getId())) {
            queryWrapper.eq("id", album.getId());
        }
        // 相册名称
        if (!StringUtils.isEmpty(album.getTitle())) {
            queryWrapper.like("title", album.getTitle());
        }
        // 相册封面
        if (!StringUtils.isEmpty(album.getImage())) {
            queryWrapper.eq("image", album.getImage());
        }
        // 图片列表
        if (!StringUtils.isEmpty(album.getImageItems())) {
            queryWrapper.eq("imageItems", album.getImageItems());
        }
        return queryWrapper;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        albumMapper.deleteById(id);
    }

    /**
     * 修改Album
     *
     * @param album
     */
    @Override
    public void update(Album album) {
        albumMapper.updateById(album);
    }

    /**
     * 增加Album
     *
     * @param album
     */
    @Override
    public void add(Album album) {
        albumMapper.insert(album);
    }

    /**
     * 根据ID查询Album
     *
     * @param id
     * @return
     */
    @Override
    public Album findById(Long id) {
        return albumMapper.selectById(id);
    }

    /**
     * 查询Album全部数据
     *
     * @return
     */
    @Override
    public List<Album> findAll() {
        return albumMapper.selectList(null);
    }
}

package com.changgou.user.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.changgou.entity.PageResult;
import com.changgou.entity.PaginationContion;
import com.changgou.user.biz.UserBiz;
import com.changgou.user.dao.UserMapper;
import com.changgou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jalen.Deng
 * @description
 * @date 2020/12/13 17:28
 **/
@Service
public class UserBizImpl implements UserBiz {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<User> getAllUser() {
        IPage<User> page = new Page(1, 10);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        List<User> records = page.getRecords();
        PageResult pageResult = new PageResult();
        pageResult.setRows(records);
        return pageResult;
    }
}

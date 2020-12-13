package com.changgou.user.biz;

import com.changgou.entity.PageResult;
import com.changgou.entity.PaginationContion;
import com.changgou.user.pojo.User;

/**
 * @author Jalen.Deng
 * @description 用户相关服务层
 * @date 2020/12/13 17:27
 **/
public interface UserBiz {
    PageResult<User> getAllUser();
}

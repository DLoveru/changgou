package com.changgou.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.changgou.entity.PageResult;
import com.changgou.entity.Result;
import com.changgou.user.biz.UserBiz;
import com.changgou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jalen.Deng
 * @description
 * @date 2020/12/13 17:56
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserBiz userBiz;

    @PostMapping(value = "/select-page")
    Result<PageResult> selectPage() {
        PageResult<User> allUser = userBiz.getAllUser();
        List<User> userList = allUser.getRows();
        System.out.println(JSONObject.toJSON(userList));
        return new Result<>();
    }
    @PostMapping("/test")
    public String test(){
        return "hello world";
    }
}

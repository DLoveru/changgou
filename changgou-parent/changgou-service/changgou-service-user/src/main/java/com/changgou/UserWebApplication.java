package com.changgou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Jalen.Deng
 * @description
 * @date 2020/12/13 17:10
 **/
@SpringBootApplication(scanBasePackages ="com.changgou")
@MapperScan("com.changgou.user.dao")
public class UserWebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(UserWebApplication.class, args);
    }
}

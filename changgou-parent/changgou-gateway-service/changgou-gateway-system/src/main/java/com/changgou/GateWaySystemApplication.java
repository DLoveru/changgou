package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Jalen.Deng
 * @description
 * @date 2020/12/19 21:24
 **/
@SpringBootApplication
@EnableEurekaClient
public class GateWaySystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWaySystemApplication.class, args);
    }
}

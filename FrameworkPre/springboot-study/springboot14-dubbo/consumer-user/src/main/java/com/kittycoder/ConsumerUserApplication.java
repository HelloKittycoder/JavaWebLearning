package com.kittycoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by shucheng on 2020/1/30 17:46
 * 1.引入依赖
 * 2.配置dubbo的注册中心地址
 * 3.引用服务
 */
@SpringBootApplication
public class ConsumerUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerUserApplication.class, args);
    }
}

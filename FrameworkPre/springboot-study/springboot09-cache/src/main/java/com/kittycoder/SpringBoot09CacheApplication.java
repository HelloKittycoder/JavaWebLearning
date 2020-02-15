package com.kittycoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by shucheng on 2020/1/30 17:46
 * @SpringBootApplication 来标注一个主程序类，说明这是一个SpringBoot应用
 */
@SpringBootApplication
@MapperScan("com.kittycoder.mapper")
@EnableCaching
public class SpringBoot09CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot09CacheApplication.class, args);
    }
}

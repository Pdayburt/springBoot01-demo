package com.lagou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.lagou.mapper")
@SpringBootApplication//扫描Spring组件并自动装配springboot
public class SpringBoot01DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot01DemoApplication.class, args);
    }


}

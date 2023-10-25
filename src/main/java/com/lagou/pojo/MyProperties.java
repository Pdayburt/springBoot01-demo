package com.lagou.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:test.properties")//配置自定义配置文件的名称和位置
@ConfigurationProperties(prefix = "test")
public class MyProperties {

    private int id;
    private String name;
}

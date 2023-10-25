package com.lagou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//表明该类为配置类
public class MyConfig {

    @Bean//返回值对象作为组件添加到Spring容器中 id 默认为方法名
    public MyService myService(){
        return new MyService();
    }



}

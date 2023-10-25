package com.lagou.springboot01demo;

import com.lagou.controller.HelloController;
import com.lagou.pojo.MyProperties;
import com.lagou.pojo.Person;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

//@RunWith(SpringRunner.class) //测试启动器 并加载S批人难过boot测试注解
@SpringBootTest//标记该类韦SpringBoot的单元测试类，并加载项目的applicationContext上下文环境
class SpringBoot01DemoApplicationTests {

    @Autowired
    private HelloController helloController;
    @Test
    void contextLoads() {
        String demo = helloController.demo();
        System.out.println(demo);
    }

    @Autowired
    private Person person;
    @Test
    void contextLoads1() {
        System.out.println(person);
    }

    @Autowired
    private MyProperties myProperties;
    @Test
    void contextLoads2() {
        System.out.println(myProperties);
    }


    @Autowired
    private ApplicationContext applicationContext;
    @Test
    void contextLoads3() {
        Object bean = applicationContext.getBean("myService");
        System.out.println(bean);
    }

    @Test
    void contextLoads4() {
        Object bean = applicationContext.getBean("simpleBean");
        System.out.println(bean);
    }




}

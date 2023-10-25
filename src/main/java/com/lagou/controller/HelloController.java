package com.lagou.controller;

import com.lagou.pojo.User;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("demo")
    public String demo(){
        return "你好，SpringBoot！！！！！";
    }

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("apiUserServiceImpl")
    private UserService apiUserServiceImpl;

    @RequestMapping("findAllUser")
    public List<User> findAllUser(){
        return userService.findAll();
    }

    @RequestMapping("findAllWithRedisTemplate")
    public List<User> findAllWithRedisTemplate(){
        return apiUserServiceImpl.findAllWithRedisTemplate();
    }



}



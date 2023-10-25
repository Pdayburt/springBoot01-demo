package com.lagou.service;

import com.lagou.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
* @author anatkh
* @description 针对表【user】的数据库操作Service
* @createDate 2023-10-26 00:11:20
*/
public interface UserService extends IService<User> {


    List<User> findAllWithRedisTemplate();

    List<User> findAll();
}

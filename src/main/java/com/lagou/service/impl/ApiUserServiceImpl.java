package com.lagou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.mapper.UserMapper;
import com.lagou.pojo.User;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
* @author anatkh
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-26 00:11:20
*/
@Service
public class ApiUserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public List<User> findAllWithRedisTemplate() {

        Object o = redisTemplate.opsForValue().get("findAllWithRedisTemplate");
        if (o != null) {
            return (List<User>) o;
        }else {
            List<User> users = userMapper.selectList(null);
            redisTemplate.opsForValue().set("findAllWithRedisTemplate",users,1, TimeUnit.DAYS);
            return users;
        }

    }

    @Cacheable(cacheNames = "user.findAll")
    @Override
    public List<User> findAll() {

        return userMapper.selectList(null);

    }
}





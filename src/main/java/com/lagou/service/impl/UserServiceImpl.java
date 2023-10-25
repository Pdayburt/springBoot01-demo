package com.lagou.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lagou.pojo.User;
import com.lagou.service.UserService;
import com.lagou.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author anatkh
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-10-26 00:11:20
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllWithRedisTemplate() {
        return null;
    }

    @Cacheable(cacheNames = "user.findAll")
    @Override
    public List<User> findAll() {

        return userMapper.selectList(null);

    }
}





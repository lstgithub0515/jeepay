package com.jeequan.jeepay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeequan.jeepay.core.entity.MetaxsireSecretKey;
import com.jeequan.jeepay.core.entity.MetaxsireUser;
import com.jeequan.jeepay.core.entity.User;
import com.jeequan.jeepay.service.mapper.UserMapper;
import com.jeequan.jeepay.service.mappermetaxsire.MetaxsireSecretKeyMapper;
import com.jeequan.jeepay.service.mappermetaxsire.MetaxsireUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lst
 * @Date: 2024/2/29
 **/
@Service
public class MetaxsireUserService extends ServiceImpl<MetaxsireUserMapper, MetaxsireUser> {

    @Autowired
    private MetaxsireUserMapper userMapper;

    @Autowired
    private UserMapper userTestMapper;


    public MetaxsireUser get() {
        return userMapper.selectById("test");
    }

    public User test() {
        return userTestMapper.selectById(1);
    }
}

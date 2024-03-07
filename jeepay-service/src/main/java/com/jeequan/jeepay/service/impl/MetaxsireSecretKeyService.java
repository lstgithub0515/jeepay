package com.jeequan.jeepay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeequan.jeepay.core.entity.MetaxsireSecretKey;
import com.jeequan.jeepay.service.mappermetaxsire.MetaxsireSecretKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lst
 * @Date: 2024/2/29
 **/
@Service
public class MetaxsireSecretKeyService extends ServiceImpl<MetaxsireSecretKeyMapper, MetaxsireSecretKey> {

    @Autowired
    private MetaxsireSecretKeyMapper secretKeyMapper;


}

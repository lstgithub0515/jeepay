package com.jeequan.jeepay.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jeequan.jeepay.core.entity.MetaxsireWallet;
import com.jeequan.jeepay.service.mappermetaxsire.MetaxsireWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lst
 * @Date: 2024/2/29
 **/
@Service
public class MetaxsireWalletService extends ServiceImpl<MetaxsireWalletMapper, MetaxsireWallet> {

    @Autowired
    private MetaxsireWalletMapper walletMapper;
}

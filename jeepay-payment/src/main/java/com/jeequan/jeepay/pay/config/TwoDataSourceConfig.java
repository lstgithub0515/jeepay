package com.jeequan.jeepay.pay.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Author: lst
 * @Date: 2024/3/1
 **/
@Configuration
public class TwoDataSourceConfig {
    @Primary
    @Bean(name = "jeepayDataSource")
    @ConfigurationProperties("spring.datasource.druid.jeepay")
    public DataSource first() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "metaxsireDataSource")
    @ConfigurationProperties("spring.datasource.druid.metaxsire")
    public DataSource second() {
        return DruidDataSourceBuilder.create().build();
    }

}

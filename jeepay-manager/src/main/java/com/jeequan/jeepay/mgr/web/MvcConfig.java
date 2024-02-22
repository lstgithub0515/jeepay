package com.jeequan.jeepay.mgr.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: lst
 * @Date: 2024/2/22
 * @Description: TODO
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // 定义映射的URL路径
                .addResourceLocations("file:E:/giteedemo/jeepay/jeepay-components/jeepay-components-oss/src/main/resources/icon/"); // 指定资源的实际存储路径
    }
}

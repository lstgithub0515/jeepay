package com.jeequan.jeepay.pay.ctrl;

import com.jeequan.jeepay.core.entity.MetaxsireUser;
import com.jeequan.jeepay.core.entity.User;
import com.jeequan.jeepay.service.impl.MetaxsireUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lst
 * @Date: 2024/3/1
 **/
@RestController
@RequestMapping
public class TestController {
    @Autowired
    MetaxsireUserService userService;

    @GetMapping("/test1")
    public MetaxsireUser test1() {
        return userService.get();
    }

    @GetMapping("/test2")
    public User test2() {
        return userService.test();
    }
}

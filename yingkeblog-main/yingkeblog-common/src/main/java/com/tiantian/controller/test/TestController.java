package com.tiantian.controller.test;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class TestController {

    @RequestMapping("/get-login-user")
    @SaCheckLogin
    public void getLoginUserInfo(){
        System.out.println("StpUtil.getExtra('username') = " + StpUtil.getExtra("username"));
        System.out.println("StpUtil.getExtra('headurl') = " + StpUtil.getExtra("headurl"));
        System.out.println("StpUtil.getExtra('uid') = " + StpUtil.getExtra("uid"));
        System.out.println("StpUtil.getExtra('email') = " + StpUtil.getExtra("email"));

        // 获取当前会话的token值
        String tokenValue = StpUtil.getTokenValue();
        System.out.println("获取当前会话的token值 = " + tokenValue);



        // 获取当前`StpLogic`的token名称
        System.out.println("获取当前StpLogic的token名称 = " + StpUtil.getTokenName());
        // 获取指定token对应的账号id，如果未登录，则返回 null
        System.out.println("StpUtil.getLoginIdByToken(tokenValue) = " + StpUtil.getLoginIdByToken(tokenValue));

        // 获取当前会话剩余有效期（单位：s，返回-1代表永久有效）
        System.out.println("StpUtil.getTokenTimeout() = " + StpUtil.getTokenTimeout());
        
        // 获取当前会话的token信息参数
        System.out.println("StpUtil.getTokenInfo() = " + StpUtil.getTokenInfo());
    }

}

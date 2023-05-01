package com.tiantian.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import com.tiantian.dto.UserDTO;
import com.tiantian.mapper.UserMapper;
import com.tiantian.pojo.Result;
import com.tiantian.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;


@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @Value("${tiantianstudy.password-key}")
    private String passwordKey;

    @PostMapping("/login")
    public Result doLogin(@RequestBody User user) {
        // 解密
        String ciphertext = SaSecureUtil.aesEncrypt(passwordKey, user.getPassword());
        User loginUser = userMapper.selectEmailAndPassword(user.getEmail(), ciphertext);

        if (Objects.isNull(loginUser)) {
            return new Result(404, "用户名或者密码错误");
        } else {
            System.out.println("===============StpUtil=============");
            // 根据账号id，进行登录 => 此处id为邮箱账号
            StpUtil.login(user.getEmail(), new SaLoginModel()
                    // Token挂载的扩展参数
                    .setExtra("username", loginUser.getUsername())
                    .setExtra("headurl", loginUser.getHeadurl())
                    .setExtra("uid", loginUser.getUid())
                    .setExtra("email", loginUser.getEmail())
                    .setIsWriteHeader(true)
                    // 设置token过期时间 7天
                    .setTimeout(60 * 60 * 24 * 7)
            );


            UserDTO userDTO = BeanUtil.copyProperties(loginUser, UserDTO.class);
            HashMap<Object, Object> res = new HashMap<>();
            res.put("userDTO",userDTO);
            res.put("TTtoken",StpUtil.getTokenInfo());
            return new Result(200, "登录成功", res);
        }
    }

    /**
     * <h2>退出登录
     */
    @PostMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    /**
     * <h2>查看登录会话是否登录
     */
    @GetMapping("/is-login")
    public Result isLogin() {
        // 查看当前会话是否登录
        boolean isLogin = StpUtil.isLogin();
        return new Result(isLogin ? 200 : 403, isLogin ? "用户已经登录" : "用户未登录无权操作");
    }

    /**
     * <h2>检查是否登录
     */
    @GetMapping("/check-login")
    public String checkLogin() {
        StpUtil.checkLogin();
        return "检查是否登录";
    }

}

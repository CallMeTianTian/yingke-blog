package com.tiantian.controller;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.StrUtil;
import com.tiantian.mapper.BaseUserInfoMapper;
import com.tiantian.mapper.UserMapper;
import com.tiantian.pojo.BaseUserInfo;
import com.tiantian.pojo.MailRequest;
import com.tiantian.pojo.Result;
import com.tiantian.pojo.User;
import com.tiantian.service.SendMailService;
import com.tiantian.utils.VerificationCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.tiantian.constants.RedisConstants.REGISTER_V_CODE;

@RestController
@RequestMapping("/register")
@CrossOrigin
public class RegisterController {

    @Autowired
    private UserMapper userMapper;

    @Value("${tiantianstudy.password-key}")
    private String passwordKey;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BaseUserInfoMapper baseUserInfoMapper;

    @PostMapping
    @Transactional // 涉及到两张表的操作 所以要开启事务
    public Result register(@RequestBody User user) {
        if (!Objects.isNull(userMapper.selectUserInfoOne(user.getEmail()))) {
            return Result.failure("该邮箱已存在");
        } else {
            // 加密存储
            user.setPassword(SaSecureUtil.aesEncrypt(passwordKey, user.getPassword()));
            // 新增用户信息
            int count1 = userMapper.insertUserInfo(user);
            Integer uid = user.getUid();
            // 初始化用户信息
            BaseUserInfo baseUserInfo = new BaseUserInfo();
            baseUserInfo.setUid(uid);
            baseUserInfo.setUsername(user.getUsername());
            // 默认语言Java
            ArrayList<String> label = new ArrayList<>(Collections.singletonList("Java"));
            baseUserInfo.setLabel(label);
            int count2 = baseUserInfoMapper.insert(baseUserInfo);
            // 定制信息
            String msg = count1 * count2 > 0 ? "注册成功" : "注册失败";
            return new Result(200, msg);
        }
    }

    @PostMapping("/simple/{email}")
    public Result SendVerificationCodeMessage(@PathVariable String email) {
        String verification6Code = VerificationCodeUtils.getVerification6Code();

        MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("应科博客");
        mailRequest.setSendTo(email);
        // 存入Redis设置5分钟之后过期
        String key = REGISTER_V_CODE + email;
        stringRedisTemplate.opsForValue().set(key, verification6Code, 5, TimeUnit.MINUTES);

        mailRequest.setText("【应科博客】，尊敬的应科博客用户，您正在注册，您的你的验证码为: " + verification6Code + "，该验证码5分钟内有效，请勿泄露他人。");
        mailRequest.setFrom("1979214069@qq.com" + "(应科博客)");
        sendMailService.sendSimpleMail(mailRequest);
        return Result.ok();
    }

    @PostMapping("/verification/{email}/{code}")
    public Result verification(@PathVariable String email, @PathVariable String code) {
        String key = REGISTER_V_CODE + email;
        String codeCache = String.valueOf(stringRedisTemplate.opsForValue().get(key));
        System.out.println("codeCache" + codeCache);
        if (codeCache == null || codeCache.equals("")) {
            return Result.failure();
        }

        if (StrUtil.equals(codeCache, code)) {
            // 验证成功后直接删除缓存 并返回状态码200给前端
            stringRedisTemplate.delete(key);
            return Result.ok();
        } else {
            return Result.failure();
        }
    }
}


package com.tiantian.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.tiantian.dto.UserDTO;
import com.tiantian.mapper.UserMapper;
import com.tiantian.pojo.BaseUserInfo;
import com.tiantian.pojo.MailRequest;
import com.tiantian.pojo.Result;
import com.tiantian.pojo.User;
import com.tiantian.service.SendMailService;
import com.tiantian.service.UserService;
import com.tiantian.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

import static com.tiantian.constants.RedisConstants.USER_CHANGE_PWD;

@RestController
@RequestMapping("/users")
@CrossOrigin
@SuppressWarnings("all")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${tiantianstudy.password-key}")
    private String passwordKey;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/{id}")
    public Result getUserInfoById(@PathVariable Long id) {
        User user = userService.getUserInfoById(id);
        user.setPassword("");
        return new Result(200, "SUCCESS", user);
    }

    @GetMapping("/base/{uid}")
    public Result getBaseUserInfoByUid(@PathVariable Integer uid) {
        BaseUserInfo baseUserInfo = userService.getBaseUserInfoByUid(uid);
        return Result.ok(baseUserInfo);
    }

    @PutMapping("/base/update")
    public Result updateBaseUserInfoByUid(@RequestBody BaseUserInfo baseUserInfo) {
        int i = userService.updateBaseUserInfoByUid(baseUserInfo);
        return Result.update(i);
    }

    @PutMapping("/update/pwd")
    public Result updatePasswrod(String password) {
        // 加密存储
        String encryptionPwd = SaSecureUtil.aesEncrypt(passwordKey, password);
        return Result.update(userService.updatePasswordByUid(encryptionPwd, UserHolder.getUid()));
    }

    @PostMapping("/base/add")
    public Result insertBaseUserInfoByUid(@RequestBody BaseUserInfo baseUserInfo) {
        int i = userService.insertBaseUserInfoByUid(baseUserInfo);
        if (i > 0) {
            return Result.ok(baseUserInfo);
        }
        return Result.failure();
    }

    @GetMapping("/dto")
    public Result getUserDTOByToken() {
        UserDTO userDTO = userMapper.selectUserDTOByUid(UserHolder.getUid());
        return Result.ok(userDTO);
    }

    @PostMapping("/simple/{email}")
    @SaCheckLogin
    public Result SendVerificationCodeMessage(@PathVariable String email) {
        // 从token中获取uid
        Integer uid = UserHolder.getUid();
        String verification6Code = RandomUtil.randomString(6);

        MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("应科博客");
        mailRequest.setSendTo(email);
        // 存入Redis设置5分钟之后过期
        String key = USER_CHANGE_PWD + uid;
        redisTemplate.opsForValue().set(key, verification6Code, 5, TimeUnit.MINUTES);

        mailRequest.setText("【应科博客】，尊敬的应科博客用户，您正在修改密码，您的你的验证码为: " + verification6Code + "，该验证码5分钟内有效，请勿泄露他人。");
        mailRequest.setFrom("1979214069@qq.com" + "(应科博客)");
        sendMailService.sendSimpleMail(mailRequest);
        return Result.ok();
    }

    @PostMapping("/verification/{code}")
    public Result verification(@PathVariable("code") String userInputCode) {
        String key = USER_CHANGE_PWD + UserHolder.getUid();
        String codeCache = String.valueOf(redisTemplate.opsForValue().get(key));

        if (codeCache == null || codeCache == "") {
            return Result.failure();
        }

        if (StrUtil.equals(codeCache, userInputCode)) {
            // 验证成功后直接删除缓存 并返回状态码200给前端
            redisTemplate.delete(key);
            return Result.ok();
        } else {
            return Result.failure();
        }
    }
}

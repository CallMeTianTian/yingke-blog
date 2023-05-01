package com.tiantian.controller;

import com.tiantian.pojo.MailRequest;
import com.tiantian.pojo.Result;
import com.tiantian.service.SendMailService;
import com.tiantian.utils.VerificationCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/send-email")
@Slf4j
@CrossOrigin
public class SendMailController {
    @Autowired
    private SendMailService sendMailService;

    @PostMapping("/simple")
    public void SendSimpleMessage(@RequestBody MailRequest mailRequest) {
        sendMailService.sendSimpleMail(mailRequest);
    }

    @PostMapping("/html")
    public void SendHtmlMessage(@RequestBody MailRequest mailRequest) {
        sendMailService.sendHtmlMail(mailRequest);
    }

    @PostMapping("/simple/{email}")
    public Result SendVerificationCodeMessage(@PathVariable String email) {
        String verification6Code = VerificationCodeUtils.getVerification6Code();
        System.out.println("用户的邮箱为 = " +  email);
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("应科博客");
        mailRequest.setSendTo(email);
        mailRequest.setText("【应科博客】，尊敬的应科博客用户，您正在修改密码，您的你的验证码为: " + verification6Code + "，该验证码5分钟内有效，请勿泄露他人。");
        mailRequest.setFrom("1979214069@qq.com" + "(应科博客)");
        sendMailService.sendSimpleMail(mailRequest);
        return new Result(200,"发送成功",verification6Code);
    }
}
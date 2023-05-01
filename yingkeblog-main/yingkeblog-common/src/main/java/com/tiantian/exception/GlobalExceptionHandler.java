package com.tiantian.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.util.SaResult;
import com.tiantian.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@Autowired
    private SendMailService sendMailService;*/

    // 全局异常拦截 
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        // 给运维人员发邮件通知前来修复
        /*MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("TianTianStudy");
        mailRequest.setSendTo("1979214069@qq.com");
        mailRequest.setText("服务器出现异常报错信息为: " + e.getMessage() + "\n请尽快前来修复");
        mailRequest.setFrom("1979214069@qq.com" + "(TianTianStudy)");
        sendMailService.sendSimpleMail(mailRequest);*/
        // 打印异常到控制台
        e.printStackTrace();
        System.out.println(e.getCause().toString());
        return SaResult.error("服务器异常");
    }

    // 全局异常拦截（拦截项目中的NotLoginException异常）
    @ExceptionHandler(NotLoginException.class)
    public Result handlerNotLoginException(NotLoginException nle) {

        // 打印堆栈，以供调试
        nle.printStackTrace();

        // 判断场景值，定制化异常信息
        String message;
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return Result.failure(message);
    }

}
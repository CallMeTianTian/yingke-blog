package com.tiantian.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MailRequest implements Serializable {
    private String sendTo; // 接收人 => 只需要这里修改即可
    
    private String from;  // 发送人

    private String subject; // 邮件主题

    private String text;// 邮件内容

    private String filePath; // 附件路径
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
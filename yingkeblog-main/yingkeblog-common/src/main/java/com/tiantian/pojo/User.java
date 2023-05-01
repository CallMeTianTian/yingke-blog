package com.tiantian.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 用户唯一id
     */
    private Integer uid;
    /**
     * 用户唯一email
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户唯一账号
     */
    private String username;

    /**
     * 用户别名
     */
    private String nickName;

    /**
     * 用户头像存储地址
     */
    private String headurl;

}
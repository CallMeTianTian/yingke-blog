package com.tiantian.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyFollowDTO {
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户头像地址
     */
    private String headurl;
    /**
     * 用户自定义的标签
     */
    private List<String> label;
}

package com.tiantian.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class BaseUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户身份
     */
    private String identity;
    /**
     * 用户性别
     */
    private Integer gender;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 用户生日
     */
    private String birthday;
    /**
     * 用户删除的语言标签
     */
    private List<String> label;
    /**
     * 用户个人描述
     */
    private String description;
    /**
     * 用户填写的公司或者名称
     */
    private String schoolOrCorporation;
}
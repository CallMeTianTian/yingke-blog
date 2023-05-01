package com.tiantian.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBlogDTO implements Serializable {
    /**
     * 博客id
     */
    private Integer blogId;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 博客标题
     */
    private String title;
    /**
     * 观看人数
     */
    private Integer watch;
    /**
     * 点赞数量
     */
    private Integer star;
    /**
     * 收藏数量
     */
    private Integer collection;
    /**
     * 发布的时间
     */
    private String releaseTime;
}

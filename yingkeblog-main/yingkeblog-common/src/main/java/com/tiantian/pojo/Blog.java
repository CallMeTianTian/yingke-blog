package com.tiantian.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable {
    /**
     * 博文id
     */
    private Integer blogId;
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 博文标题
     */
    private String title;
    /**
     * 博文内容
     */
    private String context;
    /**
     * 博文标签
     */
    private String label;
    /**
     * 博文发布的时间
     */
    private String releaseTime;
    /**
     * 博文观看人数
     */
    private Integer watch;
    /**
     * 博文点赞数量
     */
    private Integer star;
    /**
     * 博文收藏量
     */
    private Integer collection;
}

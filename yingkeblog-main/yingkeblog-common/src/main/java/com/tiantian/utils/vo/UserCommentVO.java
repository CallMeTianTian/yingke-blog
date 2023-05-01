package com.tiantian.utils.vo;

import lombok.Data;

/**
 * 思路 => add 前端传值为 blogId content
 *            后端 直接在UserHolder获取uid => 根据uid 查询到用户名和头像地址
 *                 至于时间的话 => 直接在使用工具set一下就行了
 *        show => 也是返回这个对象的的集合 让前端去遍历即可
 */
@Data
public class UserCommentVO {
    /**
     * 评论唯一id(自增)
     */
    private Integer id;
    /**
     * 被评论的博客id
     */
    private Integer blogId;
    /**
     * 当前用户id
     */
    private Integer uid;
    /**
     * 评论用户的名称
     */
    private String username;
    /**
     * 评论用户的地址
     */
    private String headurl;
    /**
     * 用户评论内容
     */
    private String content;
    /**
     * 用户评论时间(为时间戳)
     */
    private String releaseTime;
}

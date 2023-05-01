package com.tiantian.pojo;

import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 关联的用户id
     */
    private Integer followUserId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
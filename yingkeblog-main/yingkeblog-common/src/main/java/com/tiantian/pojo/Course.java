package com.tiantian.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Course implements Serializable {
    private Integer id;
    private Integer uid;
    private String className;
    private String institution;
    /**
     * 学习进度
     */
    private String progress;
    /**
     * 学习网URL
     */
    private String url;
    /**
     * 修改时间
     */
    private Date modifyTime;
}
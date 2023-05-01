package com.tiantian.pojo.question;

import lombok.Data;

import java.io.Serializable;


@Data
public class Programming implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目编号
     */
    private Integer questionId;

    /**
     * 编程语言
     */
    private String language;
    /**
     * 题目标题
     */
    private String title;

    /**
     * 题目内容
     */
    private String question;

    /**
     * 题目的例子1
     */
    private String exampleOne;

    /**
     * 题目的例子2
     */
    private String exampleTwo;

    /**
     * 正确的答案
     */
    private String rightAnswer;

    /**
     * 题目分数
     */
    private Integer score;

    /**
     * 难度
     */
    private String level;
}
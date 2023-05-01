package com.tiantian.pojo.question;

import lombok.Data;

import java.io.Serializable;


@Data
public class Choose implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目编号
     */
    private Integer questionId;

    /**
     * 题目类型
     */
    private String type;
    /**
     * 代码片段
     */
    private String exampleCode;

    /**
     * 题目内容
     */
    private String question;

    /**
     * 选项A
     */
    private String aAnswer;

    /**
     * 选项B
     */
    private String bAnswer;

    /**
     * 选项C
     */
    private String cAnswer;

    /**
     * 选项D
     */
    private String dAnswer;

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
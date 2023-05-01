package com.tiantian.pojo.question;

import lombok.Data;

import java.io.Serializable;


@Data
public class AnswerQuestions implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 题目编号
     */
    private Integer questionId;

    /**
     * 类型
     */
    private String type;

    /**
     * 题目内容
     */
    private String question;

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
package com.tiantian.pojo.question;

import lombok.Data;

import java.io.Serializable;


@Data
public class QuestionManage implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 每一天题目的id 格式 (yyyymmdd)
     */
    private Integer paperId;
    /**
     * 问题的编号
     */
    private Integer questionId;
}
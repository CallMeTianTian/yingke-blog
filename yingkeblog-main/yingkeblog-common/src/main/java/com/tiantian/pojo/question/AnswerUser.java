package com.tiantian.pojo.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AnswerUser implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 唯一id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 用户这次做题的总分数
     */
    private Integer score;

    /**
     * 用户这次做题的试卷(哪一天)
     */
    private Integer paperId;

    /**
     * 用户做编程题的答案
     */
    private String programmingAnswer;

    /**
     * 用户做编程题的得分
     */
    private Integer programmingScore;

    /**
     * 用户选择题的答案
     */
    private String chooseAnswer;

    /**
     * 用户做选择题的得分
     */
    private String chooseScore;

    /**
     * 用户做解答题的答案
     */
    @JsonProperty(value = "jQuestionAnswer")
    private String jQuestionAnswer;

    /**
     * 用户做解答题的得分
     */
    private String jQuestionScore;
}
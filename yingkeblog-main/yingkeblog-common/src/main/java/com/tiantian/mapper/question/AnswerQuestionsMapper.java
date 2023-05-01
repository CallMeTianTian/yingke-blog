package com.tiantian.mapper.question;

import com.tiantian.pojo.question.AnswerQuestions;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AnswerQuestionsMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(AnswerQuestions answerQuestions);

    AnswerQuestions selectOne();

    int updateByPrimaryKeySelective(AnswerQuestions record);

    int updateByPrimaryKey(AnswerQuestions record);

    AnswerQuestions getByQuestionId(Integer questionId);

}

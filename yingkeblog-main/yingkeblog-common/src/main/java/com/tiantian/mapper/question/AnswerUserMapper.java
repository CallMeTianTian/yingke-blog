package com.tiantian.mapper.question;

import com.tiantian.pojo.question.AnswerUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AnswerUserMapper {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(AnswerUser answerUser);

    AnswerUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnswerUser answerUser);

    int updateByPrimaryKey(AnswerUser answerUser);

    int countAnswerTodayIsAnswer(Integer paperId, Integer uid);

}

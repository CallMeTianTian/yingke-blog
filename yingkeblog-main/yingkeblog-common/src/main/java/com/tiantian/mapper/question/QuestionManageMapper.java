package com.tiantian.mapper.question;

import com.tiantian.pojo.question.QuestionManage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionManageMapper {

    int deleteByPrimaryKey(Integer paperId);

    int insert(QuestionManage questionManage);

    int insertSelective(QuestionManage questionManage);

    QuestionManage selectByPrimaryKey(Integer paperId);

    int updateByPrimaryKeySelective(QuestionManage questionManage);

    int updateByPrimaryKey(QuestionManage questionManage);

}

package com.tiantian.mapper.question;

import com.tiantian.pojo.question.Programming;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProgrammingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Programming programming);

    int insertSelective(Programming programming);

    Programming selectOne();

    int updateByPrimaryKeySelective(Programming programming);

    int updateByPrimaryKey(Programming programming);

    Programming getByQuestionId(Integer questionId);

}

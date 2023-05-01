package com.tiantian.mapper.question;

import com.tiantian.pojo.question.Choose;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChooseMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Choose choose);

    int insertSelective(Choose record);

    Choose selectOne();

    int updateByPrimaryKeySelective(Choose choose);

    int updateByPrimaryKey(Choose choose);

    Choose getByQuestionId(Integer questionId);

}

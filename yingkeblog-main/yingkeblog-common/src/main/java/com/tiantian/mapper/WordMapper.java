package com.tiantian.mapper;

import com.tiantian.pojo.Word;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordMapper {
    Word getWordAndId();
    int updateIsShowByIdInt(Integer id);
}

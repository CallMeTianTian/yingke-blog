package com.tiantian.mapper;

import com.tiantian.pojo.CountType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CountTypeMapper {
    List<CountType> countType();
}

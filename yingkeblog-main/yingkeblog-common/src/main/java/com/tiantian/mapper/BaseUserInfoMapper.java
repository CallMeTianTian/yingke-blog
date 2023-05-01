package com.tiantian.mapper;

import com.tiantian.pojo.BaseUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseUserInfoMapper {

    int insert(BaseUserInfo baseUserInfo);

    BaseUserInfo selectByUid(Integer id);

    int updateByUid(BaseUserInfo baseUserInfo);

}

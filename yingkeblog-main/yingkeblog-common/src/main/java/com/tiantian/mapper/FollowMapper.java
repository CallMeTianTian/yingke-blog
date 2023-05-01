package com.tiantian.mapper;


import com.tiantian.dto.MyFollowDTO;
import com.tiantian.pojo.Follow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FollowMapper {
    int addFollowByUid(Follow follow);

    int deleteByUserIdAndFollowUserId(Integer userId, Integer followUserid);

    int countByUserIdAndFollowUserid(Integer userId, Integer followUserid);

    List<MyFollowDTO> selectMyLikeByUserId(Integer userId);
}

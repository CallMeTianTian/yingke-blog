package com.tiantian.mapper;

import com.tiantian.utils.vo.UserCommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCommentMapper {
    List<UserCommentVO> getUserCommonsByBlogId(Integer blogId);

    int addUserComment(UserCommentVO userCommentVO);

    int deleteUserCommentByCommentIdAndUid(Integer commentId, Integer uid);
}

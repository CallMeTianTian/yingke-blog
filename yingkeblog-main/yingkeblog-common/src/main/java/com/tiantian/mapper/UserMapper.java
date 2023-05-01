package com.tiantian.mapper;

import com.tiantian.dto.UserDTO;
import com.tiantian.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectEmailAndPassword(@Param("email") String email, @Param("password") String password);

    User selectUserInfoById(Long uid);

    int insertUserInfo(User user);

    User selectUserInfoOne(@Param("email") String email);

    int updateHeadUrl(String url,Integer uid);

    String getUserHeadUrlByUid(Integer uid);

    int updatePasswordByUid(String encryptionPwd, Integer uid);

    UserDTO selectUserDTOByUid(Integer uid);
}

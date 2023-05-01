package com.tiantian.service;

import com.tiantian.pojo.BaseUserInfo;
import com.tiantian.pojo.User;

public interface UserService {
    String login(String username,String password);

    User getUserInfoById(Long uid);

    BaseUserInfo getBaseUserInfoByUid(Integer uid);

    int insertBaseUserInfoByUid(BaseUserInfo baseUserInfo);

    int updateBaseUserInfoByUid(BaseUserInfo baseUserInfo);

    int updatePasswordByUid(String encryptionPwd, Integer uid);
}

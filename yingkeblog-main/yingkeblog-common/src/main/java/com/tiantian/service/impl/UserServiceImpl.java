package com.tiantian.service.impl;

import com.tiantian.mapper.BaseUserInfoMapper;
import com.tiantian.mapper.UserMapper;
import com.tiantian.pojo.BaseUserInfo;
import com.tiantian.pojo.User;
import com.tiantian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BaseUserInfoMapper baseUserInfoMapper;

    @Override
    public String login(String username, String password) {
        userMapper.selectEmailAndPassword(username, password);
        return "1";
    }

    @Override
    public User getUserInfoById(Long uid) {
        return userMapper.selectUserInfoById(uid);
    }

    @Override
    public BaseUserInfo getBaseUserInfoByUid(Integer uid) {
        return baseUserInfoMapper.selectByUid(uid);
    }

    @Override
    public int insertBaseUserInfoByUid(BaseUserInfo baseUserInfo) {
        return baseUserInfoMapper.insert(baseUserInfo);
    }

    @Override
    public int updateBaseUserInfoByUid(BaseUserInfo baseUserInfo) {
        return baseUserInfoMapper.updateByUid(baseUserInfo);
    }

    @Override
    public int updatePasswordByUid(String encryptionPwd, Integer uid) {
        return userMapper.updatePasswordByUid(encryptionPwd, uid);
    }

}

package com.tiantian.utils;

import cn.dev33.satoken.stp.StpUtil;
import com.tiantian.dto.UserDTO;

/**
 * 从JWT中获取用户的信息
 * 此类所有方法必须在用户登录的状态下才能使用
 */
public class UserHolder {
    public static String getUsername() {
        return StpUtil.getExtra("username").toString();
    }

    public static String getHeadurl() {
        return StpUtil.getExtra("headurl").toString();
    }

    public static Integer getUid() {
        return (Integer)StpUtil.getExtra("uid");
    }

    public static String getEmail() {
        return StpUtil.getExtra("email").toString();
    }

    public static UserDTO getUserDTO() {
        return new UserDTO(getUid(), getUsername(), getEmail(), getHeadurl());
    }

}

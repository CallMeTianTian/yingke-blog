package com.tiantian.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.tiantian.mapper.UserPermissionMapper;
import com.tiantian.pojo.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

import static com.tiantian.constants.RedisConstants.USER_PERMISSION;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 先从缓存中获取用户权限信息
        Object uid = StpUtil.getExtra("uid");
        String userPermissionCache = stringRedisTemplate.opsForValue().get(USER_PERMISSION + uid.toString());

        if (StrUtil.isNotBlank(userPermissionCache)) {
            UserPermission userPermission = JSONUtil.toBean(userPermissionCache, UserPermission.class);
            return userPermission.getPermission();
        }

        // 缓存为空 => 从数据库中查询并缓存
        UserPermission userPermission = userPermissionMapper.selectPermission(Integer.parseInt(uid.toString()));
        stringRedisTemplate.opsForValue().set(USER_PERMISSION + uid,JSONUtil.toJsonStr(userPermission));
        return userPermission.getPermission();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Object uid = StpUtil.getExtra("uid");
        String userPermissionCache = stringRedisTemplate.opsForValue().get(USER_PERMISSION + uid.toString());

        if (StrUtil.isNotBlank(userPermissionCache)) {
            UserPermission userPermission = JSONUtil.toBean(userPermissionCache, UserPermission.class);
            return userPermission.getPermission();
        }

        // 缓存为空 => 从数据库中查询并缓存
        UserPermission userPermission = userPermissionMapper.selectPermission(Integer.parseInt(uid.toString()));
        stringRedisTemplate.opsForValue().set(USER_PERMISSION + uid,JSONUtil.toJsonStr(userPermission));
        return userPermission.getRole();
    }

}

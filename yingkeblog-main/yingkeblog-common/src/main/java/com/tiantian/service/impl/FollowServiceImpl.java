package com.tiantian.service.impl;


import com.tiantian.mapper.FollowMapper;
import com.tiantian.pojo.Follow;
import com.tiantian.pojo.Result;
import com.tiantian.service.FollowService;
import com.tiantian.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.tiantian.constants.RedisConstants.USER_FOLLOW;


@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private FollowMapper followMapper;

    @Override
    public Result follow(Integer followUserid, Boolean isFollow) {
        // 获取登录的用户 => 可能为空
        Integer uid = UserHolder.getUid();
        String key = USER_FOLLOW + uid;
        // 判断是关注还是取关
        if (isFollow) {
            // 关注: 新增数据 => 新增当前用户与被关注用户的关系 user_id = ? and follow_user_id 的关系
            Follow follow = new Follow();
            follow.setUserId(uid);
            follow.setFollowUserId(followUserid);
            // 保存到数据库中
            int count = followMapper.addFollowByUid(follow);
            if (count > 0) {
                stringRedisTemplate.opsForSet().add(key, followUserid.toString());
            }
        } else {
            // 取关: 删除两者关系 delete from tb_follow where user_id = ? and follow_user_id = ?
            int count = followMapper.deleteByUserIdAndFollowUserId(uid, followUserid);
            if (count > 0) {
                stringRedisTemplate.opsForSet().remove(key, followUserid.toString());
            }
        }
        return Result.ok();
    }

    @Override
    public Result isFollow(Integer followUserid) {
        Integer uid = UserHolder.getUid();
        int count = followMapper.countByUserIdAndFollowUserid(uid, followUserid);
        return Result.ok(count > 0);
    }

    @Override
    public Result queryMyLike(Integer uid) {
        return Result.ok(followMapper.selectMyLikeByUserId(uid));
    }



/*    @Override
    public Result commonFollows(Integer id) {
        // 获取登录用户
        Long userId = UserHolder.getUser().getId();
        String key = "follows:" + userId;
        // 与参数用户求交集
        Set<String> intersect = stringRedisTemplate.opsForSet().intersect(key, "follows:" + id);
        // 没有交集 => 返回空集合
        if (intersect == null || intersect.isEmpty()) {
            return Result.ok(Collections.emptyList());
        }
        // 解析id集合
        List<Long> ids = intersect.stream().map(Long::valueOf).collect(Collectors.toList());
        Stream<UserDTO> userDTOS = userService.listByIds(ids).stream()
                .map(user -> BeanUtil.copyProperties(user, UserDTO.class));

        return Result.ok(userDTOS);
    }*/
}
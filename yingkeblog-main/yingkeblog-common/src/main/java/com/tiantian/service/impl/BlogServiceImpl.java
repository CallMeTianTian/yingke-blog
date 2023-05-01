package com.tiantian.service.impl;

import com.tiantian.mapper.BlogMapper;
import com.tiantian.pojo.Result;
import com.tiantian.service.BlogService;
import com.tiantian.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.tiantian.constants.RedisConstants.BLOG_LIKED_KEY;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result likeBlog(Integer blogId) {
        // 1.获取登录用户的id
        Integer uid = UserHolder.getUid();
        // 2.先判断当前登录用户是否已经点赞
        String key = BLOG_LIKED_KEY + blogId;
        Double score = stringRedisTemplate.opsForZSet().score(key, uid.toString());
        // 3.如果未点赞 可以点赞
        if (score == null) {
            // 3.1 数据库点赞数数+1
            int res = blogMapper.likeBlogByUid(blogId);
            if (res > 0) {
                // 3.2 点赞成功保存用户到Redis的set集合
                stringRedisTemplate.opsForZSet().add(key, uid.toString(), System.currentTimeMillis());
            }
        } else {
            // 4.如果已经点赞 取消点赞
            // 4.1 数据库点赞数-1
            int res = blogMapper.UnLikeBlogByUid(blogId);
            if (res > 0) {
                // 4.2 取消点赞 把用户从Redis的set集合中移除
                stringRedisTemplate.opsForZSet().remove(key, uid.toString());
            }
        }
        return Result.ok();
    }

    @Override
    public Result isLikeBlog(Integer blogId) {
        // 1.获取登录用户的id
        Integer uid = UserHolder.getUid();
        // 2.先判断当前登录用户是否已经点赞
        String key = BLOG_LIKED_KEY + blogId;
        Double score = stringRedisTemplate.opsForZSet().score(key, uid.toString());
        // 3.不为空 => 已经点赞 => 返回状态码200
        if (score != null) {
            return Result.ok();
        }
        return Result.failure();
    }

}

package com.tiantian.controller;

import com.tiantian.mapper.BlogMapper;
import com.tiantian.mapper.UserCollectMapper;
import com.tiantian.pojo.Blog;
import com.tiantian.pojo.Result;
import com.tiantian.pojo.UserCollect;
import com.tiantian.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tiantian.constants.RedisConstants.BLOG_COLLECT_KEY;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserCollectController {

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/collect")
    public Result getMyCollect() {
        List<Blog> blogs = userCollectMapper.selectCollectsBlogsByUid(UserHolder.getUid());
        return Result.ok(blogs);
    }

    @PostMapping("/collect")
    @Transactional
    public Result addMyCollect(@RequestBody UserCollect userCollect) {
        // 1.获取登录用户的id
        Integer uid = UserHolder.getUid();
        // 2.先判断当前登录用户是否已经收藏
        Integer blogId = userCollect.getBlogId();
        String key = BLOG_COLLECT_KEY + blogId;
        Double score = stringRedisTemplate.opsForZSet().score(key, uid.toString());
        // 3.如果未收藏 可以收藏
        if (score == null) {
            // 3.1 数据库收藏数+1
            int res = blogMapper.addOneCollection(blogId);
            if (res > 0) {
                // 3.2 收藏成功保存用户到Redis的set集合
                // 3.3 添加收藏关系
                userCollect.setUserId(uid);
                userCollectMapper.insertSelective(userCollect);
                stringRedisTemplate.opsForZSet().add(key, uid.toString(), System.currentTimeMillis());
            }
        } else {
            // 4.如果已经收藏 取消收藏
            // 4.1 数据库收藏数-1
            int res = blogMapper.minusOneCollection(blogId);
            if (res > 0) {
                // 4.2 取消点赞 把用户从Redis的set集合中移除
                // 4.3 删除收藏关系
                userCollectMapper.deleteByCollectIdAndUid(blogId, uid);
                stringRedisTemplate.opsForZSet().remove(key, uid.toString());
            }
        }
        return Result.ok();
    }

    @DeleteMapping("/collect/{collectId}")
    public Result deleteCollect(@PathVariable Integer collectId) {
        return Result.delete(userCollectMapper.deleteByCollectIdAndUid(collectId, UserHolder.getUid()));
    }

    /**
     * 当点进用户界面时判断是否已经关注该用户 <br>
     * 参数: id(当前博客用户的id)
     */
    @GetMapping("/collect/or/not/{blogId}")
    public Result isCollect(@PathVariable Integer blogId) {
        int count = userCollectMapper.selectCollectByBlogIdAndUid(blogId, UserHolder.getUid());
        return Result.ok(count > 0);
    }


}

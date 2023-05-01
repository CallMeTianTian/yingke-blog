package com.tiantian.controller;

import com.tiantian.pojo.Result;
import com.tiantian.service.FollowService;
import com.tiantian.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@CrossOrigin
public class FollowController {

    @Autowired
    private FollowService followService;

    /**
    关注/取消关注用户
    参数: id(被关注人的id) <br/> 当前登录用户id从jwt中获取 <br/>isFollow(关注/取消关注)true false
    */
    @PutMapping("/{id}/{isFollow}")
    public Result follow(@PathVariable Integer id, @PathVariable Boolean isFollow){
        return followService.follow(id,isFollow);
    }

    /**
    当点进用户界面时判断是否已经关注该用户 <br>
    参数: id(当前博客用户的id)
    */
    @GetMapping("/or/not/{id}")
    public Result isFollow(@PathVariable Integer id){
        return followService.isFollow(id);
    }

    /**
     * 根据当前用户登录的id来查询我关注了谁
     */
    @GetMapping
    public Result getMyFollowByMyUid(){
        return followService.queryMyLike(UserHolder.getUid());
    }

}
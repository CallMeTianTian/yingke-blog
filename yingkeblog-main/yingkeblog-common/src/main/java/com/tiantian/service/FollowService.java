package com.tiantian.service;

import com.tiantian.pojo.Result;

public interface FollowService {
    Result follow(Integer followUserid, Boolean isFollow);

    Result isFollow(Integer followUserid);


    Result queryMyLike(Integer uid);
    // Result commonFollows(Integer id);
}

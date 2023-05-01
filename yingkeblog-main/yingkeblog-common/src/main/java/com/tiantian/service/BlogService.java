package com.tiantian.service;

import com.tiantian.pojo.Result;


public interface BlogService {
    Result likeBlog(Integer id);

    Result isLikeBlog(Integer blogId);
}

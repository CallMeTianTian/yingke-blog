package com.tiantian.mapper;

import com.tiantian.pojo.Blog;
import com.tiantian.pojo.UserCollect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 思路:
     * 用户收藏表:
     *  本次收藏的id
     *  登录用户的id
     *  用户收藏的id
     * 功能:
     *  根据登录用户的id查询收藏的博客id => 再根据博客id查询并返回给前端UserBlogDTO => 前端根据blogId直接跳转到博客页面
 *
 */
@Mapper
public interface UserCollectMapper {

    /**
     * 根据用户id删除收藏
     *
     * @param blogId
     * @param uid
     * @return int(删除记录统计)
     */
    int deleteByCollectIdAndUid(Integer blogId, Integer uid);

    /**
     * 插入收藏关系
     * @return int(插入记录统计)
     */

    int insertSelective(UserCollect userCollect);

    /**
     * 根据uid来查询收藏关系
     *
     * @param uid
     * @return UserCollect(主要返回博客id)
     */

    List<Blog> selectCollectsBlogsByUid(Integer uid);

    int selectCollectByBlogIdAndUid(Integer blogId, Integer uid);
}

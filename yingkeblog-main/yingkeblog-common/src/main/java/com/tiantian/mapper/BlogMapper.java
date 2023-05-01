package com.tiantian.mapper;

import com.tiantian.pojo.Blog;
import com.tiantian.dto.UserBlogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogMapper {
    Blog getArticleByBlogId(@Param("blogId") Integer blogId);

    List<Blog> selectAll();

    List<Blog> getBlogByLabel(String label);

    List<UserBlogDTO> getBlogByUserId(Integer id);

    List<Blog> selectByKeyWord(String keyWord);

    int addBlog(Blog blog);

    int updateBlogByUid(Blog blog);

    int deleteByBlogIdAndUserId(Integer blogId, Integer uid);

    int likeBlogByUid(Integer blogId);

    int UnLikeBlogByUid(Integer blogId);

    List<Blog> getAllBlogByUid(Integer uid);

    int addOneCollection(Integer blogId);

    int minusOneCollection(Integer blogId);
}

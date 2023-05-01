package com.tiantian.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tiantian.dto.UserBlogDTO;
import com.tiantian.mapper.BlogMapper;
import com.tiantian.mapper.UserCommentMapper;
import com.tiantian.pojo.Blog;
import com.tiantian.pojo.Result;
import com.tiantian.service.BlogService;
import com.tiantian.utils.DateFormat;
import com.tiantian.utils.UserHolder;
import com.tiantian.utils.vo.UserCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserCommentMapper userCommentMapper;

    @GetMapping("/get-all")
    public Result getAll(@RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
                         @RequestParam(value = "pageSize", defaultValue = "6", required = false) Integer pageSize) {
        // 使用PageHelper设置分页数据
        PageHelper.startPage(page, pageSize);
        // 查询到数据封装到blogPage中
        Page<Blog> blogPage = (Page<Blog>) blogMapper.selectAll();
        // 返回结果和总记录数
        String totalString = Long.toString(blogPage.getTotal());
        return new Result(200, totalString, blogPage.getResult());
    }

    @GetMapping("/get-by-type/{label}")
    public Result getByType(@PathVariable String label) {
        return Result.ok(blogMapper.getBlogByLabel(label));
    }

    @GetMapping("/article/{blogId}")
    public Result getArticleByBlogId(@PathVariable Integer blogId) {
        Blog articleByBlogId = blogMapper.getArticleByBlogId(blogId);
        return new Result(200, "SUCCESS", articleByBlogId);
    }

    @GetMapping("/me")
    public Result getAllBlogByUid() {
        return Result.ok(blogMapper.getAllBlogByUid(UserHolder.getUid()));
    }

    @GetMapping("/by-user/{id}")
    public Result getBolgDTOByUserId(@PathVariable Integer id) {
        List<UserBlogDTO> info = blogMapper.getBlogByUserId(id);
        System.out.println(info);
        return new Result(200, "SUCCESS", info);
    }

    @GetMapping("/search/{keyWord}")
    public Result SearchByKeyWord(@PathVariable String keyWord) {
        System.out.println("keyWord = " + keyWord);
        List<Blog> searchRes = blogMapper.selectByKeyWord(keyWord);
        return new Result(200, "SUCCESS", searchRes);
    }

    @PostMapping("/add")
    public Result addBlogByUid(@RequestBody Blog blog) {
        blog.setUid(UserHolder.getUid());
        blog.setUsername(UserHolder.getUsername());
        blog.setReleaseTime(DateFormat.getNowDateFormat());
        int res = blogMapper.addBlog(blog);
        String msg = res > 0 ? "提交成功" : "提交失败";
        return Result.ok(msg);
    }

    @PutMapping("/update")
    @SaCheckLogin
    public Result updateBlogByUid(@RequestBody Blog blog) {
        blog.setUid(UserHolder.getUid());
        return Result.update(blogMapper.updateBlogByUid(blog));
    }

    @DeleteMapping("/delete/{blogId}")
    @SaCheckLogin
    public Result deleteByBlogId(@PathVariable Integer blogId) {
        return Result.delete(blogMapper.deleteByBlogIdAndUserId(blogId, UserHolder.getUid()));
    }

    /**
     * 根据id进行博客点赞
     */
    @PutMapping("/like/{BlogId}")
    public Result likeBlog(@PathVariable Integer BlogId) {
        return blogService.likeBlog(BlogId);
    }

    @GetMapping("/is-like/{BlogId}")
    public Result isLikeBlog(@PathVariable Integer BlogId) {
        return blogService.isLikeBlog(BlogId);
    }

    /**
     * 根据博客id来获取所有的评论信息
     */
    @GetMapping("/comments/{blogId}")
    public Result getCommentsByBlogId(@PathVariable Integer blogId) {
        return Result.ok(userCommentMapper.getUserCommonsByBlogId(blogId));
    }

    /**
     * 添加评论
     */

    @PostMapping("/comments")
    public Result addCommentByUid(@RequestBody UserCommentVO userCommentVO) {
        // 从jwt中获取到登录用户 并设置uid
        userCommentVO.setUid(UserHolder.getUid());
        // 设置当前的时间戳
        userCommentVO.setReleaseTime(String.valueOf(System.currentTimeMillis()));
        int count = userCommentMapper.addUserComment(userCommentVO);
        return Result.delete(count);
    }

    /**
     * 删除评论
     * @param id 当前评论的id
     */
    @DeleteMapping("/comments/{id}")
    public Result deleteComments(@PathVariable Integer id) {
        return Result.delete(userCommentMapper.deleteUserCommentByCommentIdAndUid(id, UserHolder.getUid()));
    }


}

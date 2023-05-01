package com.tiantian.controller;

import com.tiantian.pojo.Course;
import com.tiantian.pojo.Result;
import com.tiantian.service.CourseService;
import com.tiantian.utils.UserHolder;
import com.tiantian.utils.vo.UserClassAndProgressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public Result getClassList() {
        Integer uid = UserHolder.getUid();
        List<Course> classList = courseService.getClassList(uid);
        return new Result(1001, "success", classList);
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Course course) {
        System.out.println(course);
        int count = courseService.updateById(course);
        String resMessage = count > 0 ? "修改成功" : "修改失败";
        return new Result(2023, resMessage);
    }


    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        List<Course> classObj = courseService.getById(id);
        return new Result(2023, "查询成功", classObj);
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        int i = courseService.deleteById(id);
        String res = i > 0 ? "删除成功" : "删除失败";
        return new Result(2023, res);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        int i = courseService.addCourse(course);
        String msg = i > 0 ? "新增成功" : "新增失败,该课程已经存在";
        int code = i > 0 ? 200 : 404;
        return new Result(code, msg);
    }

    @GetMapping("/user/{uid}")
    public Result getUserClassAndProgress(@PathVariable Integer uid) {
        Integer uid1 = UserHolder.getUid();
        List<UserClassAndProgressVO> courseList = courseService.selectUserClassAndProgress(uid1);
        return new Result(200, "SUCCESS", courseList);
    }

    @GetMapping("/follow-user/{uid}")
    public Result getFollowUserClassAndProgress(@PathVariable Integer uid) {
        List<UserClassAndProgressVO> courseList = courseService.getFollowUserClassAndProgress(uid);
        return new Result(200, "SUCCESS", courseList);
    }

    @GetMapping("/wordCount/{uid}")
    public Result getWordCountByUserId(@PathVariable Integer uid) {
        return new Result(200, "SUCCESS", courseService.wordCountByProgress(uid));
    }

}

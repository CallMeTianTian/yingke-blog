package com.tiantian.service;

import com.tiantian.pojo.Course;
import com.tiantian.utils.vo.ClassNameAndValueKeyWordVO;
import com.tiantian.utils.vo.UserClassAndProgressVO;

import java.util.List;

public interface CourseService {
    List<Course> getClassList(Integer id);

    int updateById(Course course);

    List<Course> getById(Integer id);

    int deleteById(Integer id);

    int addCourse(Course course);

    List<UserClassAndProgressVO> selectUserClassAndProgress(Integer uid);

    List<String> wordCount(Integer uid);

    List<ClassNameAndValueKeyWordVO> wordCountByProgress(Integer uid);

    List<UserClassAndProgressVO> getFollowUserClassAndProgress(Integer uid);
}

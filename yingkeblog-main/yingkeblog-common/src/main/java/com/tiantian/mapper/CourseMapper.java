package com.tiantian.mapper;

import com.tiantian.pojo.Course;
import com.tiantian.utils.vo.ClassNameAndInstitutionVO;
import com.tiantian.utils.vo.UserClassAndProgressVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    List<Course> getClassList(Integer uid);

    int updateById(Course course);

    List<Course> getById(Integer id);

    int delById(Integer id);

    int insertCourse(Course course);

    List<UserClassAndProgressVO> selectUserClassAndProgress(Integer uid);

    List<String> selectCourseClassNameByUserId(Integer id);

    List<ClassNameAndInstitutionVO> selectClassNameAndInstitutionsByUserId(Integer id);

    List<UserClassAndProgressVO> getFollowUserClassAndProgress(Integer uid);
}

package com.tiantian.mapper;

import com.tiantian.pojo.Timetable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TimetableMapper {
    List<Timetable> selectByUserId(Integer UserId);
    List<Timetable> selectByUserIdAndWeek(@Param("userId") Integer userId, @Param("week") String week);

}

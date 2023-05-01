package com.tiantian.service;

import com.tiantian.pojo.Timetable;

import java.util.List;

public interface TimetableService {
    List<Timetable> selectByUserId(Integer userId);

    List<Timetable> selectByUserIdAndWeek(Integer userId, String week);
}

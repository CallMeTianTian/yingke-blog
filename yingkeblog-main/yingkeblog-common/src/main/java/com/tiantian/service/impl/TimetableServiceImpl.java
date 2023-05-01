package com.tiantian.service.impl;


import com.tiantian.mapper.TimetableMapper;
import com.tiantian.pojo.Timetable;
import com.tiantian.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService {

    @Autowired
    private TimetableMapper timetableMapper;

    @Override
    public List<Timetable> selectByUserId(Integer userId) {
        return timetableMapper.selectByUserId(userId);
    }

    @Override
    public List<Timetable> selectByUserIdAndWeek(Integer userId, String week) {
        return timetableMapper.selectByUserIdAndWeek(userId, week);
    }
}

package com.tiantian.controller;


import com.tiantian.pojo.Result;
import com.tiantian.pojo.Timetable;
import com.tiantian.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/timetable")
@CrossOrigin
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @GetMapping("/{id}")
    @Cacheable(value = "timetableCache", key = "#.id")
    public Result getTimetableById(@PathVariable Integer id) {
        List<Timetable> list = timetableService.selectByUserId(id);
        String res = list != null ? "获取成功" : "获取失败";
        return new Result(2023,res,list);
    }

    @GetMapping("/{id}/{week}")
    @Cacheable(value = "timetableCache")
    public Result getTimetableByIdAndWeek(@PathVariable Integer id, @PathVariable String week) {
        List<Timetable> list = timetableService.selectByUserIdAndWeek(id,week);
        String res = list != null ? "获取成功" : "获取失败";
        return new Result(2023,res,list);
    }


}

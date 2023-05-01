package com.tiantian.controller.visualization;

import com.tiantian.mapper.CountTypeMapper;
import com.tiantian.pojo.CountType;
import com.tiantian.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/count")
@CrossOrigin
public class CountController {
    @Autowired
    private CountTypeMapper countTypeMapper;

    @GetMapping("/label")
    public Result getCountLabel(){
        List<CountType> countTypes = countTypeMapper.countType();
        return Result.ok(countTypes);
    }
}

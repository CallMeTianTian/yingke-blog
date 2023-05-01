package com.tiantian.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Timetable implements Serializable {
    private Integer id;
    private Integer UserId;
    private String subject;
    private String classTime;
    private String week;
}

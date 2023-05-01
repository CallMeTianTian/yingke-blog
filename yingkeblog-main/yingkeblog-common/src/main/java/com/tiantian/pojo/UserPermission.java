package com.tiantian.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UserPermission {
    private Integer id;
    private List<String> role;
    private List<String> permission;
}

package com.tiantian.pojo;

import java.io.Serializable;
import lombok.Data;


@Data
public class UserCollect implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer blogId;

    private static final long serialVersionUID = 1L;
}
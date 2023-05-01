package com.tiantian.pojo;

import lombok.Data;

import java.io.Serializable;


@Data
public class Word implements Serializable {
    /**
     * 句子唯一编号
     */
    private Integer id;
    /**
     * 句子内容
     */
    private String word;
    /**
     * 0代表还没有展示过 1代表已经展示了
     */
    private Integer isShow;
}

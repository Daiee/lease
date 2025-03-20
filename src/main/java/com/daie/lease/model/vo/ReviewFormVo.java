package com.daie.lease.model.vo;

import lombok.Data;

@Data
public class ReviewFormVo {
    private Integer rating1;

    private Integer rating2;

    private Integer rating3;

    private Integer rating4;

    private Integer rating5;

    private Integer rating6;

    private String comment;

    private Long userId;

    private Long roomId;
}

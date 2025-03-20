package com.daie.lease.model.vo;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomSearchVo {

    @Min(value = 0)
    private int pageNo = 0;

    @Min(value = 1)
    private int pageSize = 10;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String keyword;
}

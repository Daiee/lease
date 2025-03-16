package com.daie.lease.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomSearchDto {

    private int pageSize;

    private int pageNo;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;
}

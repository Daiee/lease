package com.daie.lease.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomPageVo {

    private Long id;

    private String roomName;

    private String mainPicUrl;

    private String address;
}

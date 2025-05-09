package com.daie.lease.service;

import com.daie.lease.model.pojo.Room;
import com.daie.lease.model.vo.RoomPageVo;
import com.daie.lease.model.vo.RoomSearchVo;
import org.springframework.data.domain.Page;

public interface RoomService {

    Page<RoomPageVo> getRoomPage(int index, int size);

    Room getRoomById(Long id);

    Page<Room> SearchRoomPage(RoomSearchVo roomSearchVo);
}

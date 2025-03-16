package com.daie.lease.service;

import com.daie.lease.model.pojo.Room;
import org.springframework.data.domain.Page;

public interface RoomService {

    Page<Room> getRoomPage(int index, int size);

    Room getRoomById(Long id);
}

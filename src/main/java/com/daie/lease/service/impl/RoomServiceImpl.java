package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.Room;
import com.daie.lease.repository.RoomRepository;
import com.daie.lease.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<Room> getRoomPage(int index, int size) {
        PageRequest page = PageRequest.of(1, 10);
        return roomRepository.findAll(page);
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
            .orElseThrow(RuntimeException::new);
    }
}

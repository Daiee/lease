package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.model.pojo.Room;
import com.daie.lease.model.vo.RoomPageVo;
import com.daie.lease.model.vo.RoomSearchVo;
import com.daie.lease.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("room")
public class RoomController {

    private final RoomService roomService;


    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // 分页展示房间信息
    @GetMapping("page")
    public Result<Page<RoomPageVo>> getRoomPage(int pageNo, int pageSize) {
        Page<RoomPageVo> roomPage = roomService.getRoomPage(1, 10);
        return Result.success(roomPage);
    }

    // 根据 id 查询单一房间详情
    @GetMapping("detail")
    public Result<Room> getRoomDetail(Long id) {
        return Result.success(roomService.getRoomById(id));
    }

    // 搜索房源: 最低价格，最高价格，关键词
    @GetMapping("search")
    public Result<Page<Room>> researchRoomPage(RoomSearchVo roomSearchVo) {
        return Result.success(roomService.SearchRoomPage(roomSearchVo));
    }




}

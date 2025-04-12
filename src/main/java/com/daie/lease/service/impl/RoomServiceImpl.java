package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.Room;
import com.daie.lease.model.vo.RoomPageVo;
import com.daie.lease.model.vo.RoomSearchVo;
import com.daie.lease.repository.RoomRepository;
import com.daie.lease.service.RoomService;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<RoomPageVo> getRoomPage(int index, int size) {
        PageRequest page = PageRequest.of(1, 10);
        Page<Room> roomPage = roomRepository.findAll(page);
        List<RoomPageVo> roomPageVoList = roomPage.stream()
            .map(room -> new RoomPageVo(room.getId(), room.getRoomName(), room.getCoverUrl(), room.getAddress()))
            .toList();
        return new PageImpl<>(roomPageVoList, page, roomPage.getTotalElements());
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
            .orElseThrow(RuntimeException::new);
    }

    @Override
    public Page<Room> SearchRoomPage(RoomSearchVo roomSearchVo) {
        Optional<RoomSearchVo> optionalRoomSearchVo = Optional.ofNullable(roomSearchVo);
        int pageNo = optionalRoomSearchVo.map(RoomSearchVo::getPageNo).orElse(0);
        int pageSize = optionalRoomSearchVo.map(RoomSearchVo::getPageSize).orElse(10);
        BigDecimal maxPrice = optionalRoomSearchVo.map(RoomSearchVo::getMaxPrice).orElse(null);
        BigDecimal minPrice = optionalRoomSearchVo.map(RoomSearchVo::getMinPrice).orElse(null);
        String keyword = optionalRoomSearchVo.map(RoomSearchVo::getKeyword).orElse(null);

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Specification<Room> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 处理最低价格筛选
            Optional.ofNullable(minPrice).ifPresent(price ->
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price))
            );

            // 处理最高价格筛选
            Optional.ofNullable(maxPrice).ifPresent(price ->
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), price))
            );

            // 处理关键字筛选
            Optional.ofNullable(keyword)
                .filter(s -> !s.isEmpty())
                .ifPresent(key -> {
                    Predicate namePredicate = criteriaBuilder.like(root.get("roomName"), "%" + key + "%");
                    Predicate descPredicate = criteriaBuilder.like(root.get("roomDesc"), "%" + key + "%");
                    predicates.add(criteriaBuilder.or(namePredicate, descPredicate));
                });

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return roomRepository.findAll(spec, pageable);
    }
}

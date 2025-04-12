package com.daie.lease.service.impl;

import com.daie.lease.model.pojo.BrowseHistory;
import com.daie.lease.model.pojo.Room;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.pojo.UserCollection;
import com.daie.lease.model.vo.UserCollectionPageVo;
import com.daie.lease.model.vo.UserInfoVo;
import com.daie.lease.repository.BrowseHistoryRepository;
import com.daie.lease.repository.RoomRepository;
import com.daie.lease.repository.UserCollectionRepository;
import com.daie.lease.repository.UserRepository;
import com.daie.lease.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final UserCollectionRepository userCollectionRepository;
    private final BrowseHistoryRepository browseHistoryRepository;

    public UserServiceImpl(UserRepository userRepository,
                           RoomRepository roomRepository,
                           UserCollectionRepository userCollectionRepository,
                           BrowseHistoryRepository browseHistoryRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.userCollectionRepository = userCollectionRepository;
        this.browseHistoryRepository = browseHistoryRepository;
    }

    @Override
    public UserInfoVo getUserInfoById(Long id) throws Exception {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new Exception("User not found"));
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }

    @Override
    @Transactional
    public void addToUserCollection(Long userId, Long roomId) throws Exception {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User not found"));
        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new Exception("Room not found"));

        UserCollection userCollection = new UserCollection();
        userCollection.setUser(user);
        userCollection.setRoom(room);
        userCollection.setCollectTime(new Date());

        user.getUserCollectionList().add(userCollection);
        userCollectionRepository.save(userCollection);
    }

    @Override
    @Transactional
    public void removeFromUserCollection(Long userId, Long roomId) throws Exception {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User not found"));
        UserCollection userCollection = userCollectionRepository.findByUserIdAndRoomId(userId, roomId)
            .orElseThrow(() -> new Exception("User collection not found"));

        user.getUserCollectionList().remove(userCollection);
        userCollectionRepository.delete(userCollection);
    }

    @Override
    public Page<UserCollectionPageVo> getUserCollection(Long userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Long> roomIds = userCollectionRepository.findRoomIdsByUserId(userId, pageable);
        List<UserCollectionPageVo> list = roomIds.stream().map(roomId -> {
                Optional<Room> roomOpt = roomRepository.findById(roomId);
                return roomOpt.map(item -> new UserCollectionPageVo(item.getId(), item.getRoomName(), item.getCoverUrl())).orElse(null);
            })
            .filter(Objects::nonNull)
            .toList();
        return new PageImpl<>(list, pageable, roomIds.getTotalElements());
    }

    @Override
    @Transactional
    public void addToBrowseHistory(Long userId, Long roomId) throws Exception {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new Exception("User not found"));
        Room room = roomRepository.findById(roomId)
            .orElseThrow(() -> new Exception("Room not found"));

        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setUser(user);
        browseHistory.setRoom(room);
        browseHistory.setBrowseTime(new Date());

        user.getBrowseHistoryList().add(browseHistory);
        browseHistoryRepository.save(browseHistory);
    }
}
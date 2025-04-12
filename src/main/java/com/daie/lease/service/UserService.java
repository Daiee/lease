package com.daie.lease.service;

import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.UserCollectionPageVo;
import com.daie.lease.model.vo.UserInfoVo;
import org.springframework.data.domain.Page;

public interface UserService {

    UserInfoVo getUserInfoById(Long id) throws Exception;

    void addToUserCollection(Long userId, Long roomId) throws Exception;

    void removeFromUserCollection(Long userId, Long roomId) throws Exception;

    Page<UserCollectionPageVo> getUserCollection(Long userId, int pageNo, int pageSize);

    void addToBrowseHistory(Long userId, Long roomId) throws Exception;
}

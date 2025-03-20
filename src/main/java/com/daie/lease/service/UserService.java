package com.daie.lease.service;

import com.daie.lease.model.pojo.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User getUserById(Long id) throws Exception;

    void addToUserCollection(Long userId, Long roomId) throws Exception;

    void removeFromUserCollection(Long userId, Long roomId) throws Exception;

    Page<Long> getUserCollection(Long userId, int pageNo, int pageSize) throws Exception;

    void addToBrowseHistory(Long userId, Long roomId) throws Exception;
}

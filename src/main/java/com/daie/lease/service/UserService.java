package com.daie.lease.service;

import com.daie.lease.model.pojo.User;

public interface UserService {

   User getUserById(Long id) throws Exception;
}

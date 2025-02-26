package com.daie.lease.service;

import com.daie.lease.model.pojo.User;

public interface UserService {

    User findByUsername(String username);

    String signup(String username, String password) throws Exception;

    User login(String username, String password) throws Exception;
}

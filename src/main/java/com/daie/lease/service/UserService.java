package com.daie.lease.service;

import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.LoginVo;

public interface UserService {

    User findByUsername(String username);

    String signup(User user) throws Exception;

    User login(LoginVo loginVo) throws Exception;
}

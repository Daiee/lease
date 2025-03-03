package com.daie.lease.service;

import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginVo;

public interface AuthService {
    CaptchaVo getCaptcha();

    User login(LoginVo loginVo) throws Exception;

    User signup(User user);
}

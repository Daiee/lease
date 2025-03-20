package com.daie.lease.service;

import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginVo;
import com.daie.lease.model.vo.SignupVo;

public interface AuthService {
    CaptchaVo getCaptcha();

    String login(LoginVo loginVo) throws Exception;

    String signup(SignupVo user);
}

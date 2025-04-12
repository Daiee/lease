package com.daie.lease.service;

import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginDto;
import com.daie.lease.model.vo.SignupDto;

public interface AuthService {
    CaptchaVo getCaptcha();

    String login(LoginDto loginDto) throws Exception;

    String signup(SignupDto user);
}

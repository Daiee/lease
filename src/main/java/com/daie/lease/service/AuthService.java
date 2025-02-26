package com.daie.lease.service;

import com.daie.lease.model.vo.CaptchaVo;

public interface AuthService {
    CaptchaVo getCaptcha();
    String login(String username, String password);
}

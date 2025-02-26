package com.daie.lease.controller;

import com.daie.lease.common.Result.Result;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.service.AuthService;
import com.daie.lease.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("getCaptcha")
    public Result<CaptchaVo> getCaptcha() {
        return Result.success(authService.getCaptcha());
    }

    @GetMapping("login")
    public Result<User> login(String username, String password) throws Exception {
        User loginUser = userService.login(username, password);
        assert loginUser != null;
        return Result.success(loginUser);
    }
}
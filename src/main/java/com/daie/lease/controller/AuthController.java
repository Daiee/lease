package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginVo;
import com.daie.lease.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("getCaptcha")
    public Result<CaptchaVo> getCaptcha() {
        return Result.success(authService.getCaptcha());
    }

    @PostMapping("signup")
    public Result<User> signup(@RequestBody User user) {
        return Result.success(authService.signup(user));
    }

    @GetMapping("login")
    public Result<User> login(LoginVo loginVo) throws Exception {
        User loginUser = authService.login(loginVo);
        assert loginUser != null;
        return Result.success(loginUser);
    }
}
package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginVo;
import com.daie.lease.model.vo.SignupVo;
import com.daie.lease.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("captcha")
    public Result<CaptchaVo> getCaptcha() {
        return Result.success(authService.getCaptcha());
    }

    @PostMapping("signup")
    public Result<String> signup(@RequestBody SignupVo user) {
        return Result.success(authService.signup(user));
    }

    @PostMapping("login")
    public Result<String> login(LoginVo loginVo) throws Exception {
        return Result.success(authService.login(loginVo));
    }

    @PostMapping("logout")
    public Result<String> logout() {
        return Result.success("");
    }
}
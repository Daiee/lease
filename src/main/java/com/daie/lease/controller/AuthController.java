package com.daie.lease.controller;

import com.daie.lease.common.result.Result;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginDto;
import com.daie.lease.model.vo.SignupDto;
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
    public Result<String> signup(@RequestBody SignupDto user) {
        return Result.success(authService.signup(user));
    }

    @PostMapping("login")
    public Result<String> login(@RequestBody LoginDto loginDto) throws Exception {
        return Result.success(authService.login(loginDto));
    }

    @PostMapping("logout")
    public Result<String> logout() {
        return Result.success("");
    }
}
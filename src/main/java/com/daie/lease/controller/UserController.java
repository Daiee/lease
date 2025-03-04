package com.daie.lease.controller;

import com.daie.lease.common.login.LoginUserHandler;
import com.daie.lease.common.result.Result;
import com.daie.lease.model.pojo.User;
import com.daie.lease.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("info")
    public Result<User> getInfo() throws Exception {
        Long id = LoginUserHandler.getLoginUser().getId();
        User info = userService.getUserById(id);
        return Result.success(info);
    }
}

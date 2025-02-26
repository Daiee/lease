package com.daie.lease.controller;

import com.daie.lease.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PutMapping("signup")
    public String signup(String username, String password) {
        String newUser;
        try {
            newUser = userService.signup(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newUser;
    }
}

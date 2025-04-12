package com.daie.lease.model.vo;

import lombok.Data;

@Data
public class LoginDto {

    private String username;

    private String password;

    private String captchaKey;

    private String captchaValue;
}

package com.daie.lease.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.daie.lease.common.constant.Base64PrefixConstant;
import com.daie.lease.common.utli.Base64Util;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.service.AuthService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    private final StringRedisTemplate stringRedisTemplate;

    public AuthServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public CaptchaVo getCaptcha() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        String key = "auth:login:"+ UUID.randomUUID();
        String value = captcha.getCode();
        stringRedisTemplate.opsForValue().set(key,value, 60, TimeUnit.SECONDS);
        String image = Base64Util.addPrefix(captcha.getImageBase64(), Base64PrefixConstant.BASE64_IMG);
        return new CaptchaVo(image, key);
    }

    @Override
    public String login(String username, String password) {
        return "";
    }
}

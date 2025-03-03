package com.daie.lease.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.daie.lease.common.constant.Base64PrefixConstant;
import com.daie.lease.common.utli.Base64Util;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginVo;
import com.daie.lease.repository.UserRepository;
import com.daie.lease.service.AuthService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    private final StringRedisTemplate stringRedisTemplate;
    private final UserRepository userRepository;

    public AuthServiceImpl(StringRedisTemplate stringRedisTemplate, UserRepository userRepository) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.userRepository = userRepository;
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
    public User login(LoginVo loginVo) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        String captchaKey = loginVo.getCaptchaKey();
        String captchaValue = loginVo.getCaptchaValue().toLowerCase();
        String value = stringRedisTemplate.opsForValue().get(captchaKey).toLowerCase();
        assert Objects.equals(captchaValue, value);
        User existUser = userRepository.findByUsername(username);
        assert existUser != null;
        assert Objects.equals(existUser.getPassword(), password);
        return existUser;
    }

    @Override
    public User signup(User user) {
        return userRepository.save(user);
    }
}

package com.daie.lease.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.daie.lease.common.constant.Base64PrefixConstant;
import com.daie.lease.common.utli.Base64Util;
import com.daie.lease.common.utli.JwtUtil;
import com.daie.lease.model.pojo.User;
import com.daie.lease.model.vo.CaptchaVo;
import com.daie.lease.model.vo.LoginVo;
import com.daie.lease.model.vo.SignupVo;
import com.daie.lease.repository.UserRepository;
import com.daie.lease.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    public String login(LoginVo loginVo) throws Exception {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        String captchaKey = loginVo.getCaptchaKey();
        String captchaValue = loginVo.getCaptchaValue().toLowerCase();

        String value = Optional.ofNullable(stringRedisTemplate.opsForValue().get(captchaKey))
            .orElseThrow(RuntimeException::new)
            .toLowerCase();

        if (!captchaValue.equals(value)) {
            throw new Exception("验证码错误");
        }

        User existUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("用户不存在"));
        if (!existUser.getPassword().equals(password)) {
            throw new Exception("密码错误");
        }
        return JwtUtil.createToken(existUser.getId(), existUser.getUsername());
    }

    @Override
    public String signup(SignupVo user) {
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        User save = userRepository.save(newUser);
        return "signup:" + save.getUsername() + "success";
    }
}

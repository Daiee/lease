package com.daie.lease.common.utli;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.daie.lease.common.login.LoginUser;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Optional;

@Log4j2
public class JwtUtil {

    private static final String KEY = "mS8oY4sH3xX7tM7lI1iB";

    public static String createToken(Long id, String username) {
        DateTime now = DateTime.now();
        DateTime expiration = now.offsetNew(DateField.HOUR, 24);
        HashMap<String, Object> payload = new HashMap<>();
        payload.put(JWT.ISSUED_AT, now);
        payload.put(JWT.EXPIRES_AT, expiration);
        payload.put(JWT.NOT_BEFORE, now);
        payload.put("id", id);
        payload.put("username", username);
        String token = JWTUtil.createToken(payload, KEY.getBytes());
        log.info("[token]: {}", token);
        return token;
    }

    public static LoginUser parseToken(String token) {
        Optional<String> tokenOpt = Optional.ofNullable(token);
        token = tokenOpt.orElseThrow(() -> new RuntimeException("token is null"));
        boolean verify = JWTUtil.verify(token, KEY.getBytes());
        if (!verify) {
            throw new RuntimeException("token error");
        }
        JWT jwt = JWTUtil.parseToken(token).setKey(KEY.getBytes());
        Long id = Long.valueOf(jwt.getPayload("id").toString());
        String username = jwt.getPayload("username").toString();
        LoginUser loginUser = new LoginUser(id, username);
        log.info("[loginUser]: {}", loginUser);
        return loginUser;
    }
}

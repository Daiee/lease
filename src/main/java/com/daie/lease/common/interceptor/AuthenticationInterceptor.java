package com.daie.lease.common.interceptor;

import com.daie.lease.common.login.LoginUser;
import com.daie.lease.common.login.LoginUserHandler;
import com.daie.lease.common.utli.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final Set<String> EXCLUDE_URL = new HashSet<>();

    static {
        EXCLUDE_URL.add("/auth/login");
        EXCLUDE_URL.add("/auth/captcha");
        EXCLUDE_URL.add("/auth/signup");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
        String requestURI = request.getRequestURI();
        if (EXCLUDE_URL.contains(requestURI)) {
            return true;
        }
        String token = request.getHeader("access-token");

        LoginUser loginUser = JwtUtil.parseToken(token);
        LoginUserHandler.setLoginUser(loginUser);
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) {
        LoginUserHandler.removeLoginUser();
    }
}

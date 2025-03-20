package com.daie.lease.common.login;

public class LoginUserHandler {

    private static final ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static void removeLoginUser() {
        threadLocal.remove();
    }


}

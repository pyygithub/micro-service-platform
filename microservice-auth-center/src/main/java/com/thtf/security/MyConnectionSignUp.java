package com.thtf.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * ========================
 * 没有查询到userId的用户不跳转到注册页面，默认注册一个账户
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/5 14:25
 * Version: v1.0
 * ========================
 */
//@Component
public class MyConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        // 根据社交用户信息默认创建用户并返回用户唯一标识（这里就不在注册，直接返回用户名作为唯一标识）
        return connection.getDisplayName();
    }
}

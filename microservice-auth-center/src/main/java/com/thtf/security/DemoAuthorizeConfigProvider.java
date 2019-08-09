package com.thtf.security;

import com.thtf.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * ========================
 * 具体项目的security 安全配置
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:36
 * Version: v1.0
 * ========================
 */
@Component
@Order(Integer.MAX_VALUE)
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "/user/browser/binding", // 注册请求
                "/user/app/binding", // 注册请求
                "/error",
                "/connect/*",
                "/auth/*",
                "/signin",
                "/social/signUp",  // app注册跳转服务
                "/swagger-ui.html",
                "/swagger-ui.html/**",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/**"
        ).permitAll();

        // 使用自定义的
        config.anyRequest().access("@rbacService.hasPermission(request,authentication)");
        return true;
    }
}

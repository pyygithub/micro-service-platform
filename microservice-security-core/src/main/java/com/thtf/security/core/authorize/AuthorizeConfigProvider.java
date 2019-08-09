package com.thtf.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * ========================
 * 自定义权限控制接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:16
 * Version: v1.0
 * ========================
 */
public interface AuthorizeConfigProvider {
    /**
     * @param config
     * @see HttpSecurity#authorizeRequests()
     */
    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}

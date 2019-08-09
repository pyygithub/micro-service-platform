package com.thtf.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * ========================
 * 权限自定义配置管理
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:17
 * Version: v1.0
 * ========================
 */
public interface AuthorizeConfigManager {

    void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}

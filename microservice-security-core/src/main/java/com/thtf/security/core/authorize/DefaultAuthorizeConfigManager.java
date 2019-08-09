package com.thtf.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * ========================
 * 权限自定义配置管理默认实现类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:24
 * Version: v1.0
 * ========================
 */
@Component
public class DefaultAuthorizeConfigManager implements AuthorizeConfigManager {

    @Autowired
    private List<AuthorizeConfigProvider> providers;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        for (AuthorizeConfigProvider provider : providers) {
            provider.config(config);
        }
        // 除了上面配置的，其他的都需要登录后才能访问
        config.anyRequest().authenticated();
    }
}

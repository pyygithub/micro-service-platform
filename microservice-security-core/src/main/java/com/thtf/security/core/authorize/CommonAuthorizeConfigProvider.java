package com.thtf.security.core.authorize;

import com.thtf.security.core.properties.SecurityConstants;
import com.thtf.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * ========================
 * app和browser通用静态权限配置
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:18
 * Version: v1.0
 * ========================
 */
@Component
@Order(Integer.MIN_VALUE)
public class CommonAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        // 不需要认证请求
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                securityProperties.getBrowser().getLoginPage(),
                securityProperties.getBrowser().getSignUpUrl(),
                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getBrowser().getStaticResources()
        ).permitAll();

        // 退出成功处理，没有默认值，所以需要判定下
        String signOutUrl = securityProperties.getBrowser().getSignOutUrl();
        if (signOutUrl != null) {
            config.antMatchers(signOutUrl).permitAll();
        }
    }
}

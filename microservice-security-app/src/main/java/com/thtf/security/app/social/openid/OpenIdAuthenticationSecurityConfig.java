package com.thtf.security.app.social.openid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 14:44
 * Version: v1.0
 * ========================
 */
@Component
public class OpenIdAuthenticationSecurityConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private SocialUserDetailsService userDetailsService;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        OpenIdAuthenticationProvider provider = new OpenIdAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setUsersConnectionRepository(usersConnectionRepository);

        OpenIdAuthenticationFilter filter = new OpenIdAuthenticationFilter();
        // 获取manager的是在源码中看到过
        filter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);

        // 需要一个服务提供商 和 一个过滤器
        builder.authenticationProvider(provider)
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class);
    }

}

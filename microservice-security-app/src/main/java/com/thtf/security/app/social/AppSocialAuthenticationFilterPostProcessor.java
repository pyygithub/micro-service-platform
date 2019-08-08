package com.thtf.security.app.social;

import com.thtf.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * ========================
 * app实现该配置，配置处理成功的处理器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 15:47
 * Version: v1.0
 * ========================
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;


    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        // 这里设置的其实就是之前  重构用户名密码登录里面实现的 MyAuthenticationSuccessHandler
        socialAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
    }
}

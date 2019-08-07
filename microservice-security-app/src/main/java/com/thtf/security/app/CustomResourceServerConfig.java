package com.thtf.security.app;

import com.thtf.security.core.authentication.mobile.SmsValidateCodeAuthenticationSecurityConfig;
import com.thtf.security.core.properties.SecurityConstants;
import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * ========================
 * 资源服务器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/6 17:22
 * Version: v1.0
 * ========================
 */
@Configuration
@EnableResourceServer
public class CustomResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private SmsValidateCodeAuthenticationSecurityConfig smsValidateCodeAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsValidateCodeAuthenticationSecurityConfig)
                .and()
                .apply(mySocialSecurityConfig)
                .and()
             .formLogin()
                // 当需要身份认证时，跳转到这里
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                // 自定义登录提交地址（默认： /login）
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                // 登录成功处理器
                .successHandler(myAuthenticationSuccessHandler)
                // 登录失败处理器
                .failureHandler(myAuthenticationFailureHandler)
                .and()
             .authorizeRequests()
                // 不需要认证请求
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getStaticResources(),
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/user/binding", "/session/invalid"
                ).permitAll()

                // 其它所有请求必须验证后才可以访问
                .anyRequest().authenticated()
                .and().csrf().disable();

    }
}

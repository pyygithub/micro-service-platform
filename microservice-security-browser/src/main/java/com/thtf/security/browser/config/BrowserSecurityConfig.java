package com.thtf.security.browser.config;

import com.thtf.security.core.authentication.mobile.SmsValidateCodeAuthenticationSecurityConfig;
import com.thtf.security.core.authorize.AuthorizeConfigManager;
import com.thtf.security.core.properties.SecurityConstants;
import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/30 14:48
 * Version: v1.0
 * ========================
 */
@Configuration
@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsValidateCodeAuthenticationSecurityConfig smsValidateCodeAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer customSocialSecurityConfig;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.apply(validateCodeSecurityConfig).and()
            .apply(smsValidateCodeAuthenticationSecurityConfig).and()
            .apply(customSocialSecurityConfig).and()
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
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
            .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(securityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy)
                .and()
                .and()
            .logout()
                // 配置退出的url
                .logoutUrl("/signOut")
                // 配置退出登录成功处理器
                .logoutSuccessHandler(logoutSuccessHandler)
                // 配置退出登录成功后删除cookies
                .deleteCookies("JSESSIONID")
                .and()
            .csrf()
                .disable();
        // 引入通用配置
        authorizeConfigManager.config(http.authorizeRequests());

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
		//tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}

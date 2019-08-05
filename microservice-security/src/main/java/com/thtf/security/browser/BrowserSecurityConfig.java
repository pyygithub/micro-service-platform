package com.thtf.security.browser;

import com.thtf.security.core.authentication.mobile.SmsValidateAuthenticationSecurityConfig;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.connect.web.ConnectController;
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
    private SmsValidateAuthenticationSecurityConfig smsValidateAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer mySocialSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.apply(validateCodeSecurityConfig)
                .and()
            .apply(smsValidateAuthenticationSecurityConfig)
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
            .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                .userDetailsService(userDetailsService)

                .and().authorizeRequests()
                // 不需要认证请求
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                        securityProperties.getBrowser().getLoginPage(),
                        securityProperties.getBrowser().getSignUpUrl(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        securityProperties.getBrowser().getStaticResources(),
                        "/user/binding"
                ).permitAll()

                // 其它所有请求必须验证后才可以访问
                .anyRequest().authenticated()
                .and().csrf().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
		//tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}

package com.thtf.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thtf.security.browser.support.ResponseCode;
import com.thtf.security.browser.support.ResponseResult;
import com.thtf.security.browser.support.SimpleResponse;
import com.thtf.security.core.properties.LoginResponseType;
import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.validate.code.ValidateCodeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ========================
 * Security 登录失败处理器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/31 16:45
 * Version: v1.0
 * ========================
 */
@Component("myAuthenticationFailureHandler")
@Slf4j
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败");

        if (LoginResponseType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");

            ResponseResult responseResult = null;

            if (exception instanceof ValidateCodeException) {
                ValidateCodeException validateCodeException = (ValidateCodeException) exception;
                responseResult = new ResponseResult(validateCodeException.getResponseCode()); // 验证码错误
            } else if (exception instanceof UsernameNotFoundException || exception instanceof BadCredentialsException) {
                responseResult = new ResponseResult(ResponseCode.USERNAME_OR_PASSWORD_ERROR); // 用户名或密码错误
            } else if (exception instanceof DisabledException) {
                responseResult = new ResponseResult(ResponseCode.USER_DISALBED); // 用户已被禁用
            } else if (exception instanceof LockedException) {
                responseResult = new ResponseResult(ResponseCode.USER_LOCKED); // 用户已被锁定
            } else {
                responseResult = new ResponseResult(ResponseCode.USER_LOGIN_ERROR);
            }
            response.getWriter().write(objectMapper.writeValueAsString(responseResult));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}

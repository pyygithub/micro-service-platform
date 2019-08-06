package com.thtf.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/7/31 16:00
 * Version: v1.0
 * ========================
 */
@Setter
@Getter
public class BrowserProperties {
    /** 退出成功跳转URL */
    private String signOutUrl;

    /** 默认注册页面 */
    private String signUpUrl = SecurityConstants.DEFAULT_SING_UP_URL;

    /** 默认登录页面 */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    /** 静态资源放行 */
    private String staticResources = SecurityConstants.DEFAULT_STATIC_RESOURCES;

    /** 登录方式：默认 返回JSON */
    private LoginResponseType loginType = LoginResponseType.JSON;

    /** 默认记住我超时时间：单位 秒 */
    private int rememberMeSeconds = 3600;

    /** session相关配置 */
    private SessionProperties session = new SessionProperties();
}

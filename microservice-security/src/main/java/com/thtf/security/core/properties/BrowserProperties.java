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
    private String signOutUrl = SecurityConstants.DEFAULT_SING_OUT_URL;

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    // 登录方式：默认 返回JSON
    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;
}

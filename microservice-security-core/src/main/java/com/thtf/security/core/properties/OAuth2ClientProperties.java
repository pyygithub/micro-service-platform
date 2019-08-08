package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * 认证服务器注册的第三方应用配置项
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/6 10:46
 * Version: v1.0
 * ========================
 */
@Data
public class OAuth2ClientProperties {
    /**
     * 第三方应用appId
     */
    private String clientId;

    /**
     * 第三方应用appSecret
     */
    private String clientSecret;

    /**
     * 针对此应用发出的token的有效时间
     */
    private int accessTokenValiditySeconds = 3600;

    /**
     * 针对此应用发出的refresh_token的有效时间
     */
    private int refreshTokenValiditySeconds = 2592000;

    /**
     * 支持授权类型
     */
    private String[] authorizedGrantTypes = {};

    /**
     * 信任的回调域
     */
    private String[] redirectUris = {};

    private String[] scopes = {};
}

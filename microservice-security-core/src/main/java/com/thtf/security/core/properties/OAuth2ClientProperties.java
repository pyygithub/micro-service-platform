package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * OAuth2Client配置类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/6 10:46
 * Version: v1.0
 * ========================
 */
@Data
public class OAuth2ClientProperties {
    private String clientId;

    private String clientSecret;

    private int accessTokenValiditySeconds;

    private String[] authorizedGrantTypes = {};

    /** 信任的回调域 */
    private String[] redirectUris = {};

    private String[] scopes = {};
}

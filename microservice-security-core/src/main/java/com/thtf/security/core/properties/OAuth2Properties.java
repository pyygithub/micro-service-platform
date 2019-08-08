package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * OAuth2配置类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/6 10:48
 * Version: v1.0
 * ========================
 */
@Data
public class OAuth2Properties {
    /**
     * 使用jwt时为token签名的秘钥
     */
    private String jwtSigningKey = "thtf";

    /**
     * 认证客户端配置
     */
    private OAuth2ClientProperties[] clients = {};
}

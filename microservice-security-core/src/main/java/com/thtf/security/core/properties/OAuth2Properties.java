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
    private OAuth2ClientProperties[] clients = {};
}

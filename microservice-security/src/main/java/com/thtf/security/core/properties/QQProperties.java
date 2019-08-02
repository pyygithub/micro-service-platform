package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 14:52
 * Version: v1.0
 * ========================
 */
@Data
public class QQProperties {

    private String appId;
    private String appSecret;
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 qq。
     */
    private String providerId = "qq";

}

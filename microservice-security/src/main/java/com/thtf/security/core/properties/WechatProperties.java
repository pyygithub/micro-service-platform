package com.thtf.security.core.properties;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 14:57
 * Version: v1.0
 * ========================
 */
public class WechatProperties {
    private String appId;
    private String appSecret;
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 wechat。
     */
    private String providerId = "wechat";
}

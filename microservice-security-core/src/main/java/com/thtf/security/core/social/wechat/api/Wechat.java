package com.thtf.security.core.social.wechat.api;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/5 15:02
 * Version: v1.0
 * ========================
 */
public interface Wechat {

    WechatUserInfo getUserInfo(String openId);
}

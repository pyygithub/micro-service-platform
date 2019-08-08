package com.thtf.security.core.social;

import com.thtf.security.core.support.SocialUserInfo;
import org.springframework.social.connect.Connection;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 16:26
 * Version: v1.0
 * ========================
 */
public abstract class SocialController {
    /**
     * 根据Connection信息构建SocialUserInfo
     * @param connection
     * @return
     */
    protected SocialUserInfo buildSocialUserInfo(Connection<?> connection) {
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickname(connection.getDisplayName());
        userInfo.setHeadimg(connection.getImageUrl());
        return userInfo;
    }
}

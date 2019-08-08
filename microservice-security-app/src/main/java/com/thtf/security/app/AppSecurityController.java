package com.thtf.security.app;

import com.thtf.security.app.social.AppSingUpUtils;
import com.thtf.security.core.properties.SecurityConstants;
import com.thtf.security.core.social.SocialController;
import com.thtf.security.core.support.SocialUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 16:21
 * Version: v1.0
 * ========================
 */
@RestController
public class AppSecurityController extends SocialController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSingUpUtils appSingUpUtils;

    /**
     * 需要注册时跳到这里，返回401和用户信息给前端，前端根据返回信息引导用户完成注册
     * @param request
     * @return
     */
    @GetMapping(SecurityConstants.DEFAULT_SOCIAL_SIGN_UP_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        // 从session缓存中获取三方信息
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        // 将三方信息保存到redis
        appSingUpUtils.saveConnectionData(new ServletWebRequest(request), connection.createData());
        return buildSocialUserInfo(connection);
    }


}

package com.thtf.security.core.social.wechat.connect;

import com.thtf.security.core.social.wechat.api.Wechat;
import com.thtf.security.core.social.wechat.api.WechatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * ========================
 * 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/5 15:14
 * Version: v1.0
 * ========================
 */
public class WechatServiceProvider  extends AbstractOAuth2ServiceProvider<Wechat> {
    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/jwt/access_token";

    /**
     * @param appId
     * @param appSecret
     */
    public WechatServiceProvider(String appId, String appSecret) {
        super(new WechatOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public Wechat getApi(String accessToken) {
        return new WechatImpl(accessToken);
    }

}

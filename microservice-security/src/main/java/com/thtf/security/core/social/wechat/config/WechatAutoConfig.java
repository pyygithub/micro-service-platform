package com.thtf.security.core.social.wechat.config;

import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.properties.WechatProperties;
import com.thtf.security.core.social.SocialAutoConfigurerAdapter;
import com.thtf.security.core.social.wechat.connect.WechatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * ========================
 * 微信登录配置
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/5 15:09
 * Version: v1.0
 * ========================
 */
@Configuration
@ConditionalOnProperty(prefix = "security.social.wechat", name = "appId")
public class WechatAutoConfig extends SocialAutoConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WechatProperties wechatProperties = securityProperties.getSocial().getWechat();
        return new WechatConnectionFactory(wechatProperties.getProviderId(), wechatProperties.getAppId(),
                wechatProperties.getAppSecret());
    }

//    @Bean({"connect/weixinConnect", "connect/weixinConnected"})
//    @ConditionalOnMissingBean(name = "weixinConnectedView")
//    public View weixinConnectedView() {
//        return new ImoocConnectView();
//    }

}

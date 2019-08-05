package com.thtf.security.core.social.wechat.config;

import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.properties.WechatProperties;
import com.thtf.security.core.social.CustomConnectView;
import com.thtf.security.core.social.SocialAutoConfigurerAdapter;
import com.thtf.security.core.social.wechat.connect.WechatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

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

    // 可通过自定义微信绑定视图结果实现类（wechatConnectedView）来覆盖默认视图结果实现
    @Bean({"connect/wechatConnect", "connect/wechatConnected"})
    @ConditionalOnMissingBean(name = "wechatConnectedView")
    public View wechatConnectedView() {
        return new CustomConnectView();
    }

}

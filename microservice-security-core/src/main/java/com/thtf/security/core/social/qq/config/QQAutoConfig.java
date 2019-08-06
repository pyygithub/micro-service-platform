package com.thtf.security.core.social.qq.config;

import com.thtf.security.core.properties.QQProperties;
import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.social.SocialAutoConfigurerAdapter;
import com.thtf.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * ========================
 * QQ三方登录配置
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 15:00
 * Version: v1.0
 * ========================
 */
@Configuration
@ConditionalOnProperty(prefix = "security.social.qq", name = "appId")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }

}
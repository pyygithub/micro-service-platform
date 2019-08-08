package com.thtf.security.app.social;

import com.thtf.security.core.properties.SecurityConstants;
import com.thtf.security.core.social.CustomSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ========================
 * Bean初始化处理器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 16:14
 * Version: v1.0
 * ========================
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 覆盖之前配置的Browser 默认跳转注册路径 /social/signUp
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName, "customSocialSecurityConfig")){
            CustomSpringSocialConfigurer config = (CustomSpringSocialConfigurer)bean;
            config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_SIGN_UP_URL);
            return config;
        }
        return bean;
    }
}

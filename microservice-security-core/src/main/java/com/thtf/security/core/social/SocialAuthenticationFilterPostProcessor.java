package com.thtf.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * ========================
 * SocialAuthenticationFilter后处理器，用于在不同环境下个性化社交登录的配置
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 15:30
 * Version: v1.0
 * ========================
 */
public interface SocialAuthenticationFilterPostProcessor {

    /**
     * @param socialAuthenticationFilter
     */
    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}

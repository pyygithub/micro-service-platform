package com.thtf.security.jwt;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ========================
 * 自定义JwtToken增强器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/8 15:19
 * Version: v1.0
 * ========================
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> info = new HashMap<>();
        // 需要增加的信息
        // 所以如果是需要动态的话，只能在该方法中去调用业务方法添加动态参数信息
        info.put("company", "thtf");

        // 设置附加信息
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}

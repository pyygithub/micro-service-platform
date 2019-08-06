package com.thtf.security.core.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * ========================
 * 短信验证码授权token
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 11:29
 * Version: v1.0
 * ========================
 */
public class SmsValidateCodeAuthenticationToken  extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1191387844287127247L;

    // 存放认证信息：认证前（存手机号），认证后（存认证信息)
    private final Object principal;


    public SmsValidateCodeAuthenticationToken(String mobile) {
        super(null);
        this.principal = mobile;
        setAuthenticated(false);
    }

    public SmsValidateCodeAuthenticationToken(Object principal,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}

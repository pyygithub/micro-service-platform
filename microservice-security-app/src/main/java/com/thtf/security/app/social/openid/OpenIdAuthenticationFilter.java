package com.thtf.security.app.social.openid;

import com.thtf.security.core.properties.QQProperties;
import com.thtf.security.core.properties.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 14:48
 * Version: v1.0
 * ========================
 */
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    // ~ Static fields/initializers
    // =====================================================================================
    private String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPEN_ID;

    // 服务提供商id，qq还是微信
    /** @see QQProperties#providerId */
    private String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, "POST"));
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String openId = obtainOpenId(request);
        String providerId = obtainProviderId(request);

        if (openId == null) {
            openId = "";
        }
        if (providerId == null) {
            providerId = "";
        }
        openId = openId.trim();

        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    protected String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParameter);
    }

    private String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

    protected void setDetails(HttpServletRequest request,
                              OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setOpenIdParameter(String openIdParameter) {
        Assert.hasText(openIdParameter, "Username parameter must not be empty or null");
        this.openIdParameter = openIdParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getOpenIdParameter() {
        return openIdParameter;
    }
}

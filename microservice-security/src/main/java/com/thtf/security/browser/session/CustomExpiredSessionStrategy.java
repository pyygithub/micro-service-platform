package com.thtf.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * ========================
 * 并发登录session超时处理
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/5 17:08
 * Version: v1.0
 * ========================
 */
public class CustomExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public CustomExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}

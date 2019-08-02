package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 14:57
 * Version: v1.0
 * ========================
 */
@Data
public class SocialProperties {
    // 授权过滤路径
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WechatProperties weixin = new WechatProperties();
}

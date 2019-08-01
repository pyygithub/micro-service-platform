package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:36
 * Version: v1.0
 * ========================
 */
@Data
public class SmsValidateCodeProperties {

    private int length = 6;
    private int expireIn = 60;//单位 秒

    private String url;
}

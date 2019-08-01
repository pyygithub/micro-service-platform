package com.thtf.security.core.properties;

import lombok.Data;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:34
 * Version: v1.0
 * ========================
 */
@Data
public class ValidateCodeProperties {
    private ImageValidateCodeProperties image = new ImageValidateCodeProperties();

    private SmsValidateCodeProperties sms = new SmsValidateCodeProperties();
}

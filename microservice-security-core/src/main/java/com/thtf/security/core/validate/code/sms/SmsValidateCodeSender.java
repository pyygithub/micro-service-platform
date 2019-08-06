package com.thtf.security.core.validate.code.sms;

/**
 * ========================
 * 短信验证码发送接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 11:08
 * Version: v1.0
 * ========================
 */
public interface SmsValidateCodeSender {

    void send(String mobile, String code);
}

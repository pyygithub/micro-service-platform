package com.thtf.security.core.validate.code.sms;

/**
 * ========================
 * 默认短信验证码失效类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 11:09
 * Version: v1.0
 * ========================
 */
public class DefaultSmsValidateCodeSender implements SmsValidateCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}

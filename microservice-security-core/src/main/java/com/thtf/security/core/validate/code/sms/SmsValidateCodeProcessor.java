package com.thtf.security.core.validate.code.sms;

import com.thtf.security.core.properties.SecurityConstants;
import com.thtf.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import com.thtf.security.core.validate.code.ValidateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * ========================
 * 短信验证码处理器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/2 11:06
 * Version: v1.0
 * ========================
 */
@Component("smsValidateCodeProcessor")
@Slf4j
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsValidateCodeSender smsValidateCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        log.info("短信验证码：{}", validateCode.getCode());
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsValidateCodeSender.send(mobile, validateCode.getCode());
    }
}

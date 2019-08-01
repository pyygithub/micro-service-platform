package com.thtf.security.core.validate.code;

import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.security.core.validate.code.image.ImageValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 14:13
 * Version: v1.0
 * ========================
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator codeGenerator = new ImageValidateCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

//    @Bean
//    @ConditionalOnMissingBean(SmsValidateCodeSender.class)
//    public SmsValidateCodeSender smsCodeSender() {
//        return new DefaultSmsCodeSender();
//    }

}
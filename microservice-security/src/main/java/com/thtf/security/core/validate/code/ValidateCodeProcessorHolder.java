package com.thtf.security.core.validate.code;

import com.thtf.security.browser.support.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * ========================
 * 校验码处理器持有者 处理类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:08
 * Version: v1.0
 * ========================
 */
@Component
public class ValidateCodeProcessorHolder {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (processor == null) {
            throw new ValidateCodeException(ResponseCode.VALIDATE_CODE_NOT_EXISTS);
        }
        return processor;
    }
}

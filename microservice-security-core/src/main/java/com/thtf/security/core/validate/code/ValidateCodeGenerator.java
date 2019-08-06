package com.thtf.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * ========================
 * 验证码生成器接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:32
 * Version: v1.0
 * ========================
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}

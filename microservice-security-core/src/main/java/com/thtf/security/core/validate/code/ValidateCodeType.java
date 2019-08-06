package com.thtf.security.core.validate.code;

import com.thtf.security.core.properties.SecurityConstants;

/**
 * ========================
 * 校验码类型枚举类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:08
 * Version: v1.0
 * ========================
 */
public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 获取类型参数的默认名字
     * @return
     */
    public abstract String getParamNameOnValidate();
}

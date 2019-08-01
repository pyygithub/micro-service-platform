package com.thtf.security.core.validate.code;

import com.thtf.security.browser.support.ResponseCode;
import org.springframework.security.core.AuthenticationException;

import java.text.MessageFormat;

/**
 * ========================
 * 验证码异常类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:30
 * Version: v1.0
 * ========================
 */
public class ValidateCodeException extends AuthenticationException {

    //错误代码
    ResponseCode responseCode;

    public ValidateCodeException(ResponseCode responseCode){
        super(responseCode.message());
        this.responseCode = responseCode;
    }

    public ValidateCodeException(ResponseCode resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.responseCode = resultCode;
    }

    public ResponseCode getResponseCode(){
        return responseCode;
    }

}
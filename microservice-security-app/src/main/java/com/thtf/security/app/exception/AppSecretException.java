package com.thtf.security.app.exception;

import com.thtf.security.core.support.ResponseCode;

import java.text.MessageFormat;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 16:04
 * Version: v1.0
 * ========================
 */
public class AppSecretException extends RuntimeException {

    //错误代码
    ResponseCode responseCode;

    public AppSecretException(ResponseCode responseCode){
        super(responseCode.message());
        this.responseCode = responseCode;
    }

    public AppSecretException(ResponseCode resultCode, Object... args){
        super(resultCode.message());
        String message = MessageFormat.format(resultCode.message(), args);
        resultCode.setMessage(message);
        this.responseCode = resultCode;
    }

    public ResponseCode getResponseCode(){
        return responseCode;
    }
}

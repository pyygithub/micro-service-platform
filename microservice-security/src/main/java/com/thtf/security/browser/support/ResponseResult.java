package com.thtf.security.browser.support;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * ========================
 * 统一响应结果
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 16:15
 * Version: v1.0
 * ========================
 */
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResponseResult<T> {
    //操作代码
    int code;

    //提示信息
    String message;

    //结果数据
    T data;

    public ResponseResult(ResponseCode responseCode){
        this.code = responseCode.code();
        this.message = responseCode.message();
    }

    public ResponseResult(ResponseCode responseCode, T data){
        this.code = responseCode.code();
        this.message = responseCode.message();
        this.data = data;
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(ResponseCode.SUCCESS);
    }

    public static <T> ResponseResult SUCCESS(T data){
        return new ResponseResult(ResponseCode.SUCCESS, data);
    }

    public static ResponseResult FAIL(){
        return new ResponseResult(ResponseCode.FAIL);
    }
}

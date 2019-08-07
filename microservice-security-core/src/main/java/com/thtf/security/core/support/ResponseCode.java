package com.thtf.security.core.support;

import com.alibaba.fastjson.JSON;

/**
 * ========================
 * 统一响应状态码
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 16:14
 * Version: v1.0
 * ========================
 */
public enum ResponseCode {
    /* 成功状态码 */
    SUCCESS(0,"操作成功！"),

    /* 错误状态码 */
    FAIL(-1,"操作失败！"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数格式错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),

    /* 用户认证错误：20001-29999*/
    USER_LOGIN_ERROR(20001, "用户登录失败"),
    USERNAME_OR_PASSWORD_ERROR(20002, "账号或密码错误"),
    USER_ACCOUNT_DISABLED(20003, "账号已停用，请联系管理员"),
    USER_NOT_EXIST(20004, "用户不存在"),
    USER_HAS_EXISTED(20005, "用户已存在"),
    VALIDATE_CODE_IS_BLANK(20006, "{0}验证码的值不能为空"),
    VALIDATE_CODE_NO_VALID(20007, "{0}验证码无效"),
    VALIDATE_CODE_ERROR(20008, "{0}验证码错误"),
    VALIDATE_CODE_EXPIRED(20009, "{0}验证码已过期"),
    VALIDATE_CODE_REQ_ERROR(20010, "获取{0}验证码值失败"),
    VALIDATE_CODE_NOT_EXISTS(20011, "{0}验证码不存在"),
    VALIDATE_GENERATOR_NOT_EXISTS(20012, "验证码生成器{0}不存在"),
    CLIENT_ID_REQUIRED(200013, "请在请求头中携带deviceId参数"),
    USER_DISALBED(20013, "账号已被禁用"),
    USER_LOCKED(20014, "账号被锁定"),

    /* 权限错误：70001-79999 */
    PERMISSION_UNAUTHORISE(70002,"权限不足，无权操作！"),
    PERMISSION_EXPIRE(70003,"登录状态过期！"),
    PERMISSION_LIMIT(70004, "访问次数受限制");

    int code;
    String message;

    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

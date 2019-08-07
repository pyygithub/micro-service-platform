package com.thtf.security.app.validate.code.impl;


import com.thtf.security.core.support.ResponseCode;
import com.thtf.security.core.validate.code.ValidateCode;
import com.thtf.security.core.validate.code.ValidateCodeException;
import com.thtf.security.core.validate.code.ValidateCodeRepository;
import com.thtf.security.core.validate.code.ValidateCodeType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * ========================
 * 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/7 11:21
 * Version: v1.0
 * ========================
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
    }


    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, type));
        if (value == null) {
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    /**
     * redis 验证码key值构建器
     * @param request
     * @param type
     * @return
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException(ResponseCode.CLIENT_ID_REQUIRED);
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }
}

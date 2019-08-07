package com.thtf.security.core.validate.code.impl;

import com.thtf.security.core.support.ResponseCode;
import com.thtf.security.core.validate.code.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * ========================
 * 验证码处理器抽象实现类 - 模板方法模式
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:51
 * Version: v1.0
 * ========================
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    /**
     * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        // 1. 生成验证码
        C validateCode = generate(request);
        // 2. 保存验证码
        save(request, validateCode);
        // 3. 发送验证码
        send(request, validateCode);
    }

    /**
     * 生成校验码
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException(ResponseCode.VALIDATE_GENERATOR_NOT_EXISTS, generatorName);
        }
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 保存校验码
     *
     * @param request
     * @param validateCode
     */
    private void save(ServletWebRequest request, C validateCode) {
        // 不保存图片对象到 redis session中，BufferedImage无法序列化
        // 验证时候也不需要图片对象，只要验证code是否正确就ok
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    /**
     * 发送校验码，由子类实现
     *
     * @param request
     * @param validateCode
     * @throws Exception
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 根据请求的url获取校验码的类型
     *
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {

        ValidateCodeType codeType = getValidateCodeType(request);

        C codeInSession = (C)validateCodeRepository.get(request, codeType);

                String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            log.error("获取验证码的值失败");
            e.printStackTrace();
            throw new ValidateCodeException(ResponseCode.VALIDATE_CODE_REQ_ERROR, codeType);
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(ResponseCode.VALIDATE_CODE_IS_BLANK, codeType);
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(ResponseCode.VALIDATE_CODE_NOT_EXISTS, codeType);
        }

        if (codeInSession.isExpried()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(ResponseCode.VALIDATE_CODE_EXPIRED, codeType);
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(ResponseCode.VALIDATE_CODE_ERROR, codeType);
        }

        validateCodeRepository.remove(request, codeType);
    }

}


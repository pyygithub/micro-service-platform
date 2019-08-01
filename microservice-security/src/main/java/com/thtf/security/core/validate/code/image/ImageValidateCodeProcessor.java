package com.thtf.security.core.validate.code.image;

import com.thtf.security.core.validate.code.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * ========================
 * 图片验证码处理器
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 11:49
 * Version: v1.0
 * ========================
 */
@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageValidateCode imageValidateCode) throws Exception {
        ImageIO.write(imageValidateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}
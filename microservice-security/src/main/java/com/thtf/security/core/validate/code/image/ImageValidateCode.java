package com.thtf.security.core.validate.code.image;

import com.thtf.security.core.validate.code.ValidateCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ========================
 * 图形验证码
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 10:57
 * Version: v1.0
 * ========================
 */
@Setter
@Getter
public class ImageValidateCode extends ValidateCode implements Serializable{

    private static final long serialVersionUID = -2437256583627708614L;
    private BufferedImage image;

    public ImageValidateCode(BufferedImage image, String code, int expireIn){
        super(code, expireIn);
        this.image = image;
    }

    public ImageValidateCode(BufferedImage image, String code, LocalDateTime expireTime){
        super(code, expireTime);
        this.image = image;
    }
}

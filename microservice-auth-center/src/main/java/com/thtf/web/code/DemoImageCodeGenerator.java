package com.thtf.web.code;

import com.thtf.security.core.validate.code.ValidateCodeGenerator;
import com.thtf.security.core.validate.code.image.ImageValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 14:21
 * Version: v1.0
 * ========================
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageValidateCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }

}

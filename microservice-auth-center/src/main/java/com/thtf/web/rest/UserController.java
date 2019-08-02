package com.thtf.web.rest;

import com.thtf.common.response.Result;
import com.thtf.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 10:41
 * Version: v1.0
 * ========================
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/binding")
    public void regist(User user, HttpServletRequest request) {
        // 注册用户-业务逻辑

        // 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        // String userId = user.getUserId();

        // 这里为了测试使用username作为用户唯一标识
        String userId = user.getUsername();

        // 将qq和用户绑定信息存入到数据库
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    public Result getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        //SecurityContextHolder.getContext().getAuthentication();

        return Result.SUCCESS(userDetails);
    }
}

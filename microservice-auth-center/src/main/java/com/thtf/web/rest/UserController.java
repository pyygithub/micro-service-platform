package com.thtf.web.rest;

import com.thtf.common.response.Result;
import com.thtf.security.app.social.AppSingUpUtils;
import com.thtf.security.core.properties.SecurityProperties;
import com.thtf.vo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/1 10:41
 * Version: v1.0
 * ========================
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSingUpUtils appSingUpUtils;

    @Autowired
    private SecurityProperties securityProperties;

    @PostMapping("/browser/binding")
    public void browserBinding(User user, HttpServletRequest request) {
        // 注册用户-业务逻辑

        // 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        // String userId = user.getUserId();

        // 这里为了测试使用username作为用户唯一标识
        String userId = user.getUsername();

        // 将qq和用户绑定信息存入到数据库
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @PostMapping("/app/binding")
    public void appBinding(User user, HttpServletRequest request) {
        // 注册用户-业务逻辑

        // 不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
        // String userId = user.getUserId();

        // 这里为了测试使用username作为用户唯一标识
        String userId = user.getUsername();

        // 将qq和用户绑定信息存入到数据库
        appSingUpUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    public Result getCurrentUser(@AuthenticationPrincipal UserDetails userDetails, Authentication authentication, HttpServletRequest request) throws UnsupportedEncodingException{
        //SecurityContextHolder.getContext().getAuthentication();

        // Authorization : bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjb21wYW55IjoiaW1vb2MiLCJ1c2VyX25hbWUiOiJhZG1pbiIsImp0aSI6ImRjYzVmODIwLWUwNmYtNDYyNi1hYmMyLTAyZTljZjdkZjhmOCIsImNsaWVudF9pZCI6Im15aWQiLCJzY29wZSI6WyJhbGwiXX0.nYFBXcLBN3WNef0sooNxS0s6CaEleDGfjZh7xtTEqf4
        // 增加了jwt之后，获取传递过来的token
        // 当然这里只是其中一种的 token的传递方法，自己要根据具体情况分析
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(authorization, "bearer ");
        log.info("jwt token", token);
        String jwtSigningKey = securityProperties.getOauth2().getJwtSigningKey();
        // 生成的时候使用的是 org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
        // 源码里面把signingkey变成utf8了
        // JwtAccessTokenConverter类，解析出来是一个map
        // 所以这个自带的JwtAccessTokenConverter对象也是可以直接用来解析的
        byte[] bytes = jwtSigningKey.getBytes("utf-8");
        Claims body = Jwts.parser().setSigningKey(bytes).parseClaimsJws(token).getBody();

        return Result.SUCCESS(body);
    }
}

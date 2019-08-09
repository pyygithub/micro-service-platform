package com.thtf.service.impl;

import com.thtf.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:58
 * Version: v1.0
 * ========================
 */
@Service("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();

        boolean hasPermission = false;
        // 有可能 principal 是一个Anonymous
        // 所以只要是一个UserDetails那么就能标识是经过了我们自己的数据库查询的
        // 当前需要先配置UserDetailsServices
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // 读取用户所拥有权限的所有URL
            Set<String> urls = new HashSet<>();
            // todo 查询数据库用户拥有权限url集合


            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

        }
        return hasPermission;
    }
}

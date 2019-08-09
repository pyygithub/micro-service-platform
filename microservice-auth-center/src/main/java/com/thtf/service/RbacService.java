package com.thtf.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/8/9 10:58
 * Version: v1.0
 * ========================
 */
public interface RbacService {
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

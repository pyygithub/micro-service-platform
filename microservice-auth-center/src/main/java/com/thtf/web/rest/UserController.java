package com.thtf.web.rest;

import com.thtf.common.response.Result;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/me")
    public Result getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        //SecurityContextHolder.getContext().getAuthentication();

        return Result.SUCCESS(userDetails);
    }
}


package com.xks.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

/**
 * @author xks
 * @date 2019-08-16
 */

@Controller
public class UserInfo {

    public UserDetails getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("1" + authentication.getAuthorities());
        System.out.println("2" + authentication.getCredentials());
        System.out.println("3" + authentication.toString());
        String username;
        UserDetails user = null;
        if(principal instanceof UserDetails) {
            username = ((User) principal).getUsername();
            user = (User) principal;
        }
        return user;
    }
}


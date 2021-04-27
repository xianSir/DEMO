package com.xks.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xks
 * @date 2019-08-14
 */
@RestController
public class HelloController extends UserInfo {

    @RequestMapping("/hello")
    String hello() {
        return "hello springSecurity !!!";
    }

    @RequestMapping("/user")
    String user(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getRequestedSessionId();
        String id = request.getSession().getId();
        System.out.println("121 " + sessionId + " vc 12  " + id);
        String username = "122";
        UserDetails user = getUser();
        username = user.getUsername();
        return username;
    }

}

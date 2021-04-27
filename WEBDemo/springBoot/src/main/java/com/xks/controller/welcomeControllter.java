package com.xks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xks
 * @date 2019-08-09
 */
@RestController
public class welcomeControllter {
    @RequestMapping("/")
    String home() {
        System.out.println(Thread.currentThread().getName());
        return "index";
    }
}

package com.xks.cloud.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xks
 * @date 2019-08-27
 */
@RestController
public class OneController {
    @GetMapping("get")
    public String getInfo(){
        return "11111111111111";
    }
}

package com.xks.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xks
 * @date 2019-09-02
 */
@RestController
@RefreshScope
public class HelloController {

    @Value("${falg}")
    String falg;

    @GetMapping("feign")
    public String feign() {
        return falg;
    }
}

package com.xks.cloud.service;

import org.springframework.web.bind.annotation.*;

/**
 * @author xks
 * @date 2019-09-02
 */
public interface HelloApi {

    @GetMapping("/get")
    public String helloConsumer();

    @GetMapping("get1")
    public String getInfo1(@RequestParam String username);

    @PostMapping("get2")
    public String getInfo2(@RequestBody String username );
}

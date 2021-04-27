package com.xks.cloud.client.controller;

import com.xks.cloud.service.HelloApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xks
 * @date 2019-08-27
 */
@RestController
@Slf4j
public class UserController implements HelloApi {


    public String getInfo() {
        return "hello SpringCloud";
    }

    @Override
    public String helloConsumer() {
        return "helloConsumer";
    }

    @Override
    public String getInfo1(@RequestParam String username) {
        return "hello SpringCloud :" + username;
    }

    @Override
    public String getInfo2(@RequestBody String username) {
        return "hello SpringCloud:" + username;
    }
}

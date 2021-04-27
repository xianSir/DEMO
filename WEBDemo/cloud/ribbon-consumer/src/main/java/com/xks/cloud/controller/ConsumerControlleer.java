package com.xks.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.xks.cloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xks
 * @date 2019-08-28
 */
@RestController
public class ConsumerControlleer {

    @Autowired
    HelloService service;

    @GetMapping("/get")
    public String helloConsumer(){

        return  service.helloConsumer();
    }
}

package com.xks.controller;

import com.xks.bean.myBean;
import com.xks.service.exampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xks
 * @date 2019-08-07
 */
@RestController
public class example extends BaseController {
    @Autowired
    exampleService exampleService;
    @Autowired
    myBean bean;


    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "hello world !";
    }

    @RequestMapping("/example")
    String example() {
        return exampleService.getMessage();
    }


}

package com.xks.application;

import com.xks.conf.spring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.HashMap;

/**
 * @author xks
 * @date 2019-08-07
 */
@SpringBootApplication
@Import(spring.class)
public class startApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setWebApplicationType(WebApplicationType.SERVLET);
        //禁用命令行参数  example: -- server.port = 8090
        application.setAddCommandLineProperties(false);
        application.run(startApplication.class, args);
    }
}

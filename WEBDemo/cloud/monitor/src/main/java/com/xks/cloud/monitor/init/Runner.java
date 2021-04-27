package com.xks.cloud.monitor.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author xks
 * @date 2019-08-07
 * 两个接口以相同的方式工作并提供单个run方法，该方法在SpringApplication.run(…​)完成之前调用 。
 * CommandLineRunner  ApplicationRunner
 * 单元测试会有问题  暂时没有找到解决办法
 */
@Component
public class Runner implements ApplicationRunner {

    @Autowired
    ServletWebServerApplicationContext web;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + web.getWebServer().getPort() + web.getApplicationName() + "/");
    }
}

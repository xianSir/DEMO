package com.xks.cloud;

import feign.Feign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient //服务
@EnableHystrix
@EnableCircuitBreaker //熔断器
@EnableFeignClients  //feign
@EnableHystrixDashboard //dashboard
public class FeignApplication /*extends SpringBootServletInitializer*/{

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

}

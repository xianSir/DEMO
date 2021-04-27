package com.xks.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

//@EnableCircuitBreaker  //开启断路器
@SpringCloudApplication  //
@EnableHystrixDashboard
public class RibbonConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonConsumerApplication.class, args);
    }

}

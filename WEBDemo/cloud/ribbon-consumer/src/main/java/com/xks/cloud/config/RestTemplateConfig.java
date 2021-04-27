package com.xks.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author xks
 * @date 2019-08-28
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    @LoadBalanced //开启客户端负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

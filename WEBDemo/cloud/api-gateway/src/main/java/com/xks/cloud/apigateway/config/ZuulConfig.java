package com.xks.cloud.apigateway.config;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * @author xks
 * @date 2019-09-03
 */
public class ZuulConfig {

    //配置路由映射规则
    @Bean
    public PatternServiceRouteMapper serviceRouteMapper(){
        return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "$(version)/$(name)");
    }
}

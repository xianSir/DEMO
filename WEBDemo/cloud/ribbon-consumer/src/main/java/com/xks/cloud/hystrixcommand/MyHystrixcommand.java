/*
package com.xks.cloud.hystrixcommand;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

*/
/**
 * @author xks
 * @date 2019-08-30
 *//*

public class MyHystrixcommand extends HystrixCommand {
    @Autowired
    RestTemplate restTemplate;

    protected MyHystrixcommand(HystrixCommandGroupKey group) {
        super(group);
    }

    protected MyHystrixcommand(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool) {
        super(group, threadPool);
    }

    protected MyHystrixcommand(HystrixCommandGroupKey group, int executionIsolationThreadTimeoutInMilliseconds) {
        super(group, executionIsolationThreadTimeoutInMilliseconds);
    }

    protected MyHystrixcommand(HystrixCommandGroupKey group, HystrixThreadPoolKey threadPool, int executionIsolationThreadTimeoutInMilliseconds) {
        super(group, threadPool, executionIsolationThreadTimeoutInMilliseconds);
    }

    protected MyHystrixcommand(Setter setter) {
        super(setter);
    }

    @Override
    protected Object run() throws Exception {
        return    restTemplate.getForEntity("http://SERVER-EUREKA/get", String.class).getBody();
    }
}
*/

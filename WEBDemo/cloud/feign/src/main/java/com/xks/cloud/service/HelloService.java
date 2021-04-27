package com.xks.cloud.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xks
 * @date 2019-09-02
 */
@FeignClient("server-eureka")
public interface HelloService  extends HelloApi {


}

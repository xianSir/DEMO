package com.xks.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

/**
 * @author xks
 * @date 2019-08-29
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;



    @HystrixCommand(fallbackMethod = "back")
    @CacheResult(cacheKeyMethod = "")
    @CacheRemove(cacheKeyMethod = "",commandKey = "")
    public String helloConsumer() {
        //测试断路器
      /*  try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
      //同步
        ResponseEntity<String> entity = restTemplate.getForEntity("http://SERVER-EUREKA/get", String.class);
        HttpStatus httpStatus = entity.getStatusCode();
        new AsyncResult<String>(){
            @Override
            public String invoke() {
                return null;
            }
        };
        return entity.getBody();
    }
    public String back(){
        return "测试数据成功";
    }
}

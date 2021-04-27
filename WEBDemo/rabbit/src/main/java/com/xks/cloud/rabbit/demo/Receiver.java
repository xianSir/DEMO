package com.xks.cloud.rabbit.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-09-05
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver {

    @RabbitHandler
    public void process(String str) {
        System.out.println("rabbitMessage:      "+str);
    }

}

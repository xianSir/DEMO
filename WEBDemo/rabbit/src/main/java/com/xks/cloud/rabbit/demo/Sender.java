package com.xks.cloud.rabbit.demo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-09-05
 */
@Component
public class Sender {

    @Autowired
    RabbitTemplate template;

    public void sendMessage(){
        template.convertAndSend("hello","hello RabbitMq");
    }
}

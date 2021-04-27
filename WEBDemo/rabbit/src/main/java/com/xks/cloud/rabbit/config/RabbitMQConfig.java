package com.xks.cloud.rabbit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xks
 * @date 2019-09-05
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}

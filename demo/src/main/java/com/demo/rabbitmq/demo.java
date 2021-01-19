package com.demo.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Return;
import com.rabbitmq.client.ReturnCallback;
import com.rabbitmq.client.ShutdownSignalException;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xks
 * @date 2021-01-18
 */
public class demo {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("123456");
        connectionFactory.setVirtualHost("/");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        AMQP.Exchange.DeclareOk declareOk = channel.exchangeDeclare("XKS", "direct");
        AMQP.Queue.DeclareOk demo = channel.queueDeclare("demo", true, false, false, null);
        channel.queueBind("demo","XKS","xks-demo");

        channel.basicPublish("XKS", "xks-demo",null , "123456".getBytes());
        channel.basicConsume("demo", new DefaultConsumer(channel) {
            @Override
            public void handleConsumeOk(String consumerTag) {
                System.out.println("消费者注册"+consumerTag);
            }

            @Override
            public void handleCancelOk(String consumerTag) {
                System.out.println("消费者---意外--退出---调用");
            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {
                System.out.println("消费者 --退出-- 调用");
            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
                System.out.println("通讯关闭");
            }

            @Override
            public void handleRecoverOk(String consumerTag) {
                System.out.println("通讯回复继续消费+"+consumerTag);
            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("获取消费信息  :"+new String(body)+" 。"+envelope.toString());
                if(envelope.isRedeliver()){
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }else {
                    channel.basicRecover();
                }
            }
        });
    }

}

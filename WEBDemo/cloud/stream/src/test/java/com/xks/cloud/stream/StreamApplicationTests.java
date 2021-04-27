package com.xks.cloud.stream;

import com.xks.cloud.stream.exchanges.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreamApplication.class)
public class StreamApplicationTests {

    @Autowired
    SinkSender sender;

    @Test
    public void contextLoads() {
        sender.output().send(MessageBuilder.withPayload("123456").build());
    }

}

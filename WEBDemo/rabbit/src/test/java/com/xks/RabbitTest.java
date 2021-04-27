package com.xks;

import com.xks.cloud.rabbit.RabbitApplication;
import com.xks.cloud.rabbit.demo.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xks
 * @date 2019-09-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class RabbitTest {

    @Autowired
    Sender sender;

    @Test
    public void hello(){
        sender.sendMessage();
    }
}

package com.xks.cloud.stream.demo;

import com.xks.cloud.stream.exchanges.SinkSender;
import jdk.internal.org.objectweb.asm.tree.analysis.Interpreter;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @author xks
 * @date 2019-09-05
 */
@EnableBinding(value = {Sink.class, SinkSender.class})
public class SinkReceiver {

    @StreamListener("out")
    public void process(String str) {
        System.out.println("rabbitMessage:-----------"+str);
    }

}

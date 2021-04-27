package com.xks.cloud.stream.exchanges;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

/**
 * @author xks
 * @date 2019-09-06
 */
public interface SinkSender {

    @Output("out")
    MessageChannel output();

}

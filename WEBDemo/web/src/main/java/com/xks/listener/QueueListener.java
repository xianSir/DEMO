package com.xks.listener;

import com.xks.common.DeferredResultHolder;
import com.xks.service.MockQueue;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-08-02
 */

@Slf4j
@Component
public class QueueListener implements ApplicationListener {
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        new Thread(() -> {
            while (true) {
                if(StringUtils.isNotBlank(mockQueue.getCompeleteOrder())) {
                    String orderNum = mockQueue.getCompeleteOrder();
                    log.info("返回订单处理结果" + orderNum);
                    deferredResultHolder.getMap().get(orderNum).setResult("success");
                    mockQueue.setCompeleteOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}


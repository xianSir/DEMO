package com.xks.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author xks
 * @date 2019-08-02
 */
@Component
public class MockQueue {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(MockQueue.class);
    private String placeOrder;

    private String compeleteOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            log.info("收到下单的请求");
            this.placeOrder = placeOrder;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.compeleteOrder = placeOrder;
            log.info("完成下单的请求");
        }).start();
    }

    public String getCompeleteOrder(DeferredResult<String> result) {
        new Thread(() -> {
            log.info("简单的");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setResult("业务处理完成请检查-------------------------------------------");
            log.info("完成简单处理");
        }).start();
        return compeleteOrder;
    }

    public String getCompeleteOrder() {
        return compeleteOrder;
    }

    public void setCompeleteOrder(String compeleteOrder) {
        this.compeleteOrder = compeleteOrder;
    }
}

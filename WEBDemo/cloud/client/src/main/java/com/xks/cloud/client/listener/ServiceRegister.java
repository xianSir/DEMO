package com.xks.cloud.client.listener;

import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-08-27
 * InstancePreRegisteredEvent 服务注册之前
 * InstanceRegisteredEvent 服务注册事件
 */
@Component
public class ServiceRegister implements ApplicationListener<InstanceRegisteredEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(InstanceRegisteredEvent event) {
        System.out.println("-----------------------------服务注册--------------------------------------");
    }
}

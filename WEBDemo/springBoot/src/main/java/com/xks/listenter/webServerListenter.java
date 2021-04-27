package com.xks.listenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xks
 * @date 2019-08-08
 */
//@Component
public class webServerListenter implements ApplicationListener<WebServerInitializedEvent> {
    @Autowired
    WebApplicationContext web;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            System.out.println("http://" + InetAddress.getLocalHost().getHostAddress() + ":" + event.getWebServer().getPort() + web.getApplicationName() + "/");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}

package com.xks.conf;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author xks
 * @date 2021-04-22
 */
@Component
public class AfterSpring implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;
    private String contextPath;
    private String serverIp;

    private String serverUrl;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServerApplicationContext applicationContext = event.getApplicationContext();
        TomcatWebServer webServer = (TomcatWebServer)event.getWebServer();
        try {
            this.serverIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort = webServer.getPort();
        Environment env = applicationContext.getEnvironment();
        String path = env.getProperty("server.servlet.context-path");
        this.serverUrl = "http://" + serverIp + ":" + serverPort + path + "/doc.html";
        System.out.println(serverUrl);
    }
}

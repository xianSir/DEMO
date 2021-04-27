package com.xks.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.HashSet;

/**
 * 配置servlet
 *
 * @author xks
 * @date 2019-08-14
 */
public class Servlet implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        HashSet<ErrorPage> set = new HashSet<>();
        set.add(new ErrorPage(HttpStatus.NOT_FOUND, "error.html"));
        factory.setErrorPages(set);
    }
}

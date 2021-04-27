package com.xks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xks
 * @date 2019-08-14
 */
@Slf4j
@Configuration
@EnableAsync
public class WebMVC implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("配置静态资源位置");
        //配置静态资源路径
        registry.addResourceHandler("/**")
                .addResourceLocations("resources/", "static/", "public/",
                        "META-INF/resources/")
                .addResourceLocations("classpath:resources/", "classpath:static/",
                        "classpath:public/", "classpath:META-INF/resources/", "/");
    }
}


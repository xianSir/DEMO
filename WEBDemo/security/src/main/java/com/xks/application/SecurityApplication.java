package com.xks.application;

import com.xks.config.Spring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(Spring.class)
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication();
        application.setWebApplicationType(WebApplicationType.SERVLET);
        SpringApplication.run(SecurityApplication.class, args);
    }

}

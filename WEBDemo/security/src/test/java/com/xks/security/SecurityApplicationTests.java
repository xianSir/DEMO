/*
package com.xks.security;

import com.xks.application.SecurityApplication;
import com.xks.config.Spring;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityApplication.class})

public class SecurityApplicationTests {

    @Autowired
    ApplicationContext applicationContext;
    @Test
    @WithMockUser(username = "xks",password = "123456",roles = {"user"})
    public void contextLoads() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());;
    }

}
*/

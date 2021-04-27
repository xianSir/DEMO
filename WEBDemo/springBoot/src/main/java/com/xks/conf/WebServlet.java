package com.xks.conf;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 暂不使用(不会)
 *
 * @author xks
 * @date 2019-08-09
 */
//@ServletComponentScan
public class WebServlet {
    /**
     * 设置匹配*.action后缀请求
     *
     * @param dispatcherServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<DispatcherServlet> servletServletRegistrationBean = new ServletRegistrationBean<>(dispatcherServlet);
        servletServletRegistrationBean.addUrlMappings("*.do");
        return servletServletRegistrationBean;
    }
}

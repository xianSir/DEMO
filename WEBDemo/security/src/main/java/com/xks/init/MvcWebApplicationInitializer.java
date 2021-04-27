package com.xks.init;

import com.xks.config.WebMVC;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author xks
 * @date 2019-08-15
 * 确保加载到 mvc
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebMVC.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}

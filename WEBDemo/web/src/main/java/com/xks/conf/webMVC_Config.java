package com.xks.conf;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.context.request.async.TimeoutDeferredResultProcessingInterceptor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import java.util.List;


/**
 * @author xks
 * @date 2019-08-05
 */
/*@Configuration
@EnableWebMvc*/
public class webMVC_Config implements WebMvcConfigurer {
    /**
     * 异步
     *
     * @param configurer
     */
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerCallableInterceptors(new TimeoutCallableProcessingInterceptor());
        configurer.registerDeferredResultInterceptors(new TimeoutDeferredResultProcessingInterceptor());
        configurer.setDefaultTimeout(5000);
        configurer.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }

    /**
     * <mvc:resources mapping="/" location="WEB-INF/.."/>
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        new DefaultServletHttpRequestHandler();
        registry.addResourceHandler("/").addResourceLocations("WEB-INF/..");
        //registry.hasMappingForPattern("/");
    }

    /**
     * <mvc:default-servlet-handler />
     *
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
        converters.add(new FastJsonHttpMessageConverter());
    }

    /**
     * 视图解析
     *
     * @param registry
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/", ".jsp");
    }

    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Bean
    CommonsMultipartResolver getMultipartResolverBean() {
        return new CommonsMultipartResolver();
    }
}

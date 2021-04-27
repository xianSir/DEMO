package com.xks.conf;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xks
 * @date 2019-08-08
 */
@Configuration
public class webMVC implements WebMvcConfigurer {

//    @Override
//    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {
//        //false 不使用后缀匹配
//        //true 使用后缀匹配 如  rest/hello  =  rest/hello.do/.action
//        //pathMatchConfigurer.setUseSuffixPatternMatch(true);
//    }

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(true);
//    }

    /**
     * addViewControllers可以方便的实现一个请求直接映射成视图，而无需书写controller
     * registry.addViewController("请求路径").setViewName("请求页面文件路径")
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index").setViewName("index.html");
    }

    /**
     * <mvc:resources mapping="/" location="WEB-INF/.."/>
     *
     * @param registry
     */
   /* public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("WEB-INF/..");
    }*/

    /**
     * <mvc:default-servlet-handler />
     *
     * @param configurer
     */
    /*public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/

    /**
     * @param converters
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.APPLICATION_JSON_UTF8);
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setSupportedMediaTypes(list);
        converters.add(converter);
    }

    @Bean
    CommonsMultipartResolver getMultipartResolverBean() {
        return new CommonsMultipartResolver();
    }
}

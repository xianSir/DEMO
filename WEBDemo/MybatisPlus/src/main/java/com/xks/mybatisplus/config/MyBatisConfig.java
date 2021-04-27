package com.xks.mybatisplus.config;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xks
 * @date 2019-10-25
 */
@Configuration
public class MyBatisConfig {

    /**
     * 添加分页插件
     * @return
     */
    @Bean
    public  PaginationInterceptor setPaginationInterceptor(){

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        return paginationInterceptor;
    }

}

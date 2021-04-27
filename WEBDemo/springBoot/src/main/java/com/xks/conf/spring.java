package com.xks.conf;

import com.xks.bean.myRandom;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;

/**
 * 注  @PropertySource注释无法加载YAML文件
 *
 * @author xks
 * @Import()
 * @ImportResource()
 * @PropertySources()
 * @date 2019-08-07
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties({myRandom.class})
@ComponentScan(basePackages = "com.xks")
@PropertySources({
        @PropertySource("/config/configProperties.yaml"),
        @PropertySource("/conf/random.yaml"),
        @PropertySource("/conf/random.properties"),
})
//@Profile("dev") //限制profile 为dev 时使用 (application.yaml中配置)
public class spring {
}

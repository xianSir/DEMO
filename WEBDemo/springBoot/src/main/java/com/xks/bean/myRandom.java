package com.xks.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-08-08
 * 或 @EnableConfigurationProperties（myRandom.class）
 */
@Component
@Data
@ConfigurationProperties(prefix = "random", ignoreUnknownFields = false)
public class myRandom {
    String secret;
    String number;
    String bignumber;
    String uuid;
    String ten;
    String range;
}

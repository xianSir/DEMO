package com.xks.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-08-07
 */
@Component
@Data
public class myBean {
    @Value("${name}")
    String name;
    //注入随机数
    //@Value(" ${random.value}") or  @Value(" ${number}")
    @Value(" ${random.number}")
    String secret;
    @Value("${age}")
    String age;

}

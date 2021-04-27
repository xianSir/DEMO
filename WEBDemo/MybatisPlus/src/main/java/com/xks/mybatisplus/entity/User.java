package com.xks.mybatisplus.entity;

import lombok.Data;

/**
 * @author xks
 *
 * @date 2019-10-25
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

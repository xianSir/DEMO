package com.xks.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xks
 * @date 2021-04-15
 */
@Data
public class BaseQuery implements Serializable {

    private static final long serialVersionUID = 7932554525438695637L;

    String batch;
    String name;
    String passWord;
}

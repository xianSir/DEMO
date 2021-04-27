package com.xks.service;

import org.springframework.stereotype.Service;

/**
 * @author xks
 * @date 2019-08-02
 */
@Service
public class taskService {
    public String task() throws InterruptedException {
        Thread.sleep(5000);
        return "ceshi 888888888888888888";
    }
}

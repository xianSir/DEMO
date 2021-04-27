package com.xks.common;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 异步容器 存储其他线程信息
 *
 * @author xks
 * @date 2019-08-02
 */


@Component
public class DeferredResultHolder {
    private Map<String, DeferredResult<String>> map = new HashMap<String, DeferredResult<String>>();
    DeferredResult<String> result = new DeferredResult<>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}
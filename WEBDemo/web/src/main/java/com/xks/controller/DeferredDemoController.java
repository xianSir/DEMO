package com.xks.controller;


import com.xks.common.DeferredResultHolder;
import com.xks.service.MockQueue;
import com.xks.service.taskService;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Callable;

/**
 * springmvc 异步学习
 *
 * @author xks
 * @date 2019-08-02
 * servlet异步请求处理的简要概述：
 * A ServletRequest可以通过调用置于异步模式request.startAsync()。
 * 这样做的主要作用是Servlet（以及任何过滤器）可以退出，但响应保持打开状态以便稍后处理完成。
 * 对request.startAsync()返回的调用AsyncContext，可用于进一步控制异步处理。例如，它提供的dispatch方法类似于Servlet API的转发，
 * 但它允许应用程序在Servlet容器线程上恢复请求处理。
 * 在ServletRequest提供对电流DispatcherType，它可以使用处理该初始请求，异步调度，正向，以及其他的调度类型之间进行区分。
 */
@RestController
public class DeferredDemoController {
    private static Logger log = LoggerFactory.getLogger(DeferredDemoController.class);
    @Autowired
    taskService service;
    @Autowired
    MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    /**
     * DeferredResult 处理工作如下：
     * <p>
     * 控制器返回a DeferredResult并将其保存在可以访问它的某个内存中队列或列表中。
     * <p>
     * Spring MVC调用request.startAsync()。
     * <p>
     * 同时，DispatcherServlet所有已配置的过滤器都会退出请求处理线程，但响应仍保持打开状态。
     * <p>
     * 应用程序DeferredResult从某个线程设置，Spring MVC将请求调度回Servlet容器。
     * <p>
     * 将DispatcherServlet被再次调用，并且处理与异步生产返回值恢复。
     * <p>
     * <p>
     * 使用注意事项:
     * 1. 需要把 DeferredResult 变量传递过去  作用: 返回 返回值
     * 2. springmvc 监听异步返回之后http相应返回
     */
    @GetMapping("/demo")
    @ResponseBody
    public DeferredResult demmo(ServletRequest request, ServletResponse response) throws InterruptedException {
        //开启servlet 异步请求 同事 servlet退出请求 但线程任进行处理
        log.info("主线程开始");
        String orderNo = RandomUtils.nextInt() + "";
        // mockQueue.setPlaceOrder(orderNo);
        DeferredResult<String> result = new DeferredResult<String>();
        mockQueue.getCompeleteOrder(result);
        //  deferredResultHolder.getMap().put(orderNo, result);
        log.info("主线程结束");
        return result;
    }

    /**
     * Callable 处理工作如下：
     * <p>
     * 控制器返回一个Callable。
     * <p>
     * Spring MVC调用request.startAsync()并将其提交Callable到a TaskExecutor以在单独的线程中进行处理
     * <p>
     * 同时，DispatcherServlet所有过滤器都退出Servlet容器线程，但响应仍保持打开状态。
     * <p>
     * 最终Callable产生一个结果，Spring MVC将请求发送回Servlet容器以完成处理。
     * <p>
     * 将DispatcherServlet被再次调用，并且处理从所述异步生产返回值恢复Callable。
     */
    @GetMapping("/demo1")
    @ResponseBody
    public Callable callDemo() {
        return new Callable() {
            @Override
            public Object call() throws Exception {
                return "6666666666666666666";
            }
        };
    }

    @GetMapping("/demo2")
    @ResponseBody
    public String demo2(ServletRequest request, ServletResponse response) throws InterruptedException {
        return "88888888888";
    }

    @GetMapping(path = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handle() {
        SseEmitter emitter = new SseEmitter();
        return emitter;
    }

    @GetMapping("/download")
    public StreamingResponseBody handleStream() {

        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                // write...
            }
        };
    }
}

package com.xks.listenter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author xks
 * 或实现InitializingBean接口
 * @date 2019-08-07
 * ServletWebServerInitializedEvent
 * ApplicationStartingEvent是在一个运行的开始，但任何处理之前被发送，除了听众和初始化的注册。
 * ApplicationEnvironmentPreparedEvent当被发送Environment到中已知的上下文中使用，但是在创建上下文之前。
 * ApplicationPreparedEvent刷新开始前，刚刚发，但之后的bean定义已经被加载。
 * ApplicationStartedEvent上下文已被刷新后发送，但是任何应用程序和命令行亚军都被调用前。
 * ApplicationReadyEvent任何应用程序和命令行亚军被呼叫后发送。它表示应用程序已准备好为请求提供服务。
 * ApplicationFailedEvent如果在启动时异常发送。
 */
@Component
public class initListenter implements ApplicationListener<ApplicationStartedEvent>, ApplicationContextAware {
    /**
     * Handle an application.yml event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        System.out.println("*******************springweb 启动完成*******************");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}

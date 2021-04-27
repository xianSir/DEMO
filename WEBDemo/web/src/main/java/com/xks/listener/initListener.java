package com.xks.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-07-23
 * spring事件
 */
@Component
public class initListener implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * @param ContextRefreshEvent，当ApplicationContext容器初始化完成或者被刷新的时候，就会发布该事件。
     *                      比如调用ConfigurableApplicationContext接口中的refresh()方法。
     *                      此处的容器初始化指的是所有的Bean都被成功装载，后处理（post-processor）Bean被检测到并且激活，
     *                      所有单例Bean都被预实例化，ApplicationContext容器已经可以使用。只要上下文没有被关闭，
     *                      刷新可以被多次触发。XMLWebApplicationContext支持热刷新，GenericApplicationContext不支持热刷新。
     * @param ContextStartedEvent，当ApplicationContext启动的时候发布事件，
     *                      即调用ConfigurableApplicationContext接口的start方法的时候。
     *                      这里的启动是指，所有的被容器管理生命周期的Bean接受到一个明确的启动信号。在经常需要停止后重新启动的场合比较适用。
     * @param ContextStoppedEvent，当ApplicationContext容器停止的时候发布事件，
     *                      即调用ConfigurableApplicationContext的close方法的时候。
     *                      这里的停止是指，所有被容器管理生命周期的Bean接到一个明确的停止信号。
     * @param ContextClosedEvent，当ApplicationContext关闭的时候发布事件，
     *                      即调用ConfigurableApplicationContext的close方法的时候，
     *                      关闭指的是所有的单例Bean都被销毁。关闭上下后，不能重新刷新或者重新启动。
     * @param RequestHandledEvent，只能用于DispatcherServlet的web应用，Spring处理用户请求结束后，系统会触发该事件。
     */
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     *              <p>
     *              使用ContextRefreshEvent 会存在一个问题，在web 项目中（spring mvc），系统会存在两个容器，一个是root application context ,
     *              另一个就是我们自己的 projectName-servlet context（作为root application context的子容器）。
     *              <p>
     *              这种情况下，就会造成onApplicationEvent方法被执行两次。为了避免上面提到的问题，
     *              我们可以只在root application context初始化完成后调用逻辑代码，其他的容器的初始化完成，则不做任何处理，修改后代码.
     *              解决方法获取父对象  spring的对象为null
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        ApplicationContext parent = context.getParent();
        String displayName = context.getDisplayName();
        Object source = event.getSource();
        System.out.println("::::::::::::" + source);
        System.out.println("-------------" + displayName + "时间 " + event.getTimestamp() + "-------------applicationName:" + context.getApplicationName());
        if(parent != null) {
            System.out.println(parent.getApplicationName());
        }
        System.out.println("SPring启动完成*******************************************************");
    }
}

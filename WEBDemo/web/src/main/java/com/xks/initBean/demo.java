package com.xks.initBean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author xks
 * @date 2019-08-02
 */
@Component
public class demo implements InitializingBean {
    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println("demo class 初始化完成");
    }
}

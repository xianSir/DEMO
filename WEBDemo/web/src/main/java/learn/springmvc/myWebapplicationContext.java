package learn.springmvc;


import com.xks.conf.appConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author xks
 * @date 2019-07-24
 */
/*public class myWebapplicationContext  implements WebApplicationInitializer  {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Load Spring web application configuration
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(appConfig.class);
        ac.refresh();
        //获取servelet容器
        ServletContext servletCxt= ac.getServletContext();

        // Create and register the DispatcherServlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/app/*");
        //获取webapplicationcontext
       // RequestContextUtils.findWebApplicationContext()
    }

    private  static ClassPathXmlApplicationContext  xmlstart() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("conf/spring-context.xml");
        return context;
    }

}*/

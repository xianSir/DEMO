#注spring-dubbo 单元测试使用中注意点
spring dubbo  dubbo依赖于容器(tomcat jetty) 
单元测试是必须先启动dubbo提供方服务,注意测试时spring容器重加载造成bean duplicate(重复) 
解决方案 去掉spring包扫描,防止spring再次加载dubbo服务或服务的实现类

#spring 笔记
spring 注解注入bean 如实现接口默认获取interface 
    解决方案: 加入 <aop:aspectj-autoproxy proxy-target-class="true"/>  
        强制spring使用cglib代理来实现 (spring 默认使用java的动态代理(通过接口))
        
InitializingBean:spring管理bean的生命周期 初始化回调  接口允许bean 执行初始化工作 bean初始化完成后设置属性

ServletContextAware/ApplicationContextAware :代码注入 context

#springmvc
webApplicationContext层次结构
![Image text](https://docs.spring.io/spring/docs/5.2.0.M3/spring-framework-reference/images/mvc-context-hierarchy.png)
配置  FormContentFilter/ HiddenHttpMethodFilter 过滤器 是springmvc支持"PUT", "PATCH", "DELETE"请求(浏览器不支持 springmvc 内部转换)
    HttpPutFormContentFilter只支持put


#log4j2.0版本及以上只支持xml json 不支持properties
























































#大型应用程序 spring 中银引入增加启动速度
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-indexer</artifactId>
    <version>5.0.2.RELEASE</version>
    <optional>true</optional>
</dependency>
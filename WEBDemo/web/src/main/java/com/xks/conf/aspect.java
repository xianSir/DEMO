package com.xks.conf;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


/**
 * aop配置
 *
 * @author xks
 * @date 2019-07-23
 * <p>
 * xml <aop:aspectj-autoproxy/>
 */
//声明切面
@Component
@Aspect
public class aspect {
    /**
     * set开头的函数
     */
    @Pointcut("execution(* set*(..))")
    public void exe() {
    }

    /**
     * 参数为
     *
     * @args 为参数为注解如 @mark 的函数
     */
    //@Pointcut("@args(com.xks.annotation.mark)")
    @Pointcut("args(learn.person)")
    public void arg() {
    }

    /**
     * execution：用于匹配方法执行连接点。这是使用Spring AOP时使用的主要切入点指示符。
     * <p>
     * within：限制匹配某些类型中的连接点（使用Spring AOP时在匹配类型中声明的方法的执行）。
     * <p>
     * this：限制与连接点的匹配（使用Spring AOP时执行方法），其中bean引用（Spring AOP代理）是给定类型的实例。
     * <p>
     * target：限制与连接点的匹配（使用Spring AOP时执行方法），其中目标对象（被代理的应用程序对象）是给定类型的实例。
     * <p>
     * args：限制与连接点的匹配（使用Spring AOP时执行方法），其中参数是给定类型的实例。
     *
     * @target：限制与连接点的匹配（使用Spring AOP时执行方法），其中执行对象的类具有给定类型的注释。 target 值为注释 且注释在类上
     * @args：限制与连接点的匹配（使用Spring AOP时执行方法），其中传递的实际参数的运行时类型具有给定类型的注释。
     * @within：限制匹配到具有给定注释的类型中的连接点（使用Spring AOP时在具有给定注释的类型中声明的方法的执行）。
     * @annotation：限制连接点的匹配，其中连接点的主题（在Spring AOP中执行的方法）具有给定的注释。
     */
    //@Pointcut("execution(* methodName(..))")
    //@Pointcut("target(com.xks.learn.person)")
    //@Pointcut("within(com.xks.mineFace)")
    //@Pointcut("target(com.xks.mineFace)")
    @Pointcut("@target(com.xks.annotation.mark)")
    public void annotation() {
    }

    /**
     * 当前对象
     */
    @Pointcut("this(learn.person)")
    public void th() {
    }

    /**
     * 被注解mark标记的类或函数
     */
    @Pointcut("@annotation(com.xks.annotation.mark)")
    public void ann() {
    }

    @AfterReturning(pointcut = "annotation()")
    public void out() {
        System.out.println("spring  aop 正在使用中----------------------- ");
    }
}

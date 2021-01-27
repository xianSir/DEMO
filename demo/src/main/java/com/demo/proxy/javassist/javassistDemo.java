package com.demo.proxy.javassist;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.ProxyFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author xks
 * @date 2021-01-27
 */
public class javassistDemo {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        ProxyFactory proxyFactory = new ProxyFactory();
        Class aClass = proxyFactory.createClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    /**
     * 更具抽象接口创建class
     * @param clazz
     * @return
     * @throws Exception
     */
    public Object getProxy(Class clazz) throws Exception{

        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置需要创建的子类
        proxyFactory.setSuperclass(clazz);
        proxyFactory.setHandler((self, thisMethod, proceed, args) -> {
            System.out.println("---- 开始拦截 ----");
            Object result = proceed.invoke(self, args);
            System.out.println("---- 结束拦截 ----");

            return result;
        });
        return proxyFactory.createClass().newInstance();

    }

    public static void createByteCode() throws Exception{
        ClassPool classPool = ClassPool.getDefault();
        CtClass cc = classPool.makeClass("com.cxuan.proxypattern.UserDaoImpl");

        // 设置接口
        CtClass ctClass = classPool.get("com.cxuan.proxypattern.UserDao");
        cc.setInterfaces(new CtClass[] {ctClass});

        // 创建方法
        CtMethod saveUser = CtMethod.make("public void saveUser(){}", cc);
        saveUser.setBody("System.out.println(\"---- 插入用户 ----\");");
        cc.addMethod(saveUser);

        Class c = cc.toClass();
        cc.writeFile("/class/cxuan-justdoit");

    }
}

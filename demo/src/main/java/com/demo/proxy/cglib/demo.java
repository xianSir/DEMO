package com.demo.proxy.cglib;



import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xks
 * @date 2021-01-15
 */
public class demo {


    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new AutoMethodInterceptor());

        UserService userService = (UserService)enhancer.create();

        userService.saveUser();
    }
}

class AutoMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("---- 方法拦截 ----" +method.getName()+"   "+methodProxy.getSuperName());
        Object object = methodProxy.invokeSuper(obj, args);
        return object;
    }
}

class UserService {
    public UserService() {
    }

    public void saveUser(){
        System.out.println("---- 保存用户 ----");
    }
}
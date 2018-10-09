package com.yl.job.task;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Alex
 * @since 2018/10/9 13:50
 */
public class JdkProxy {

    public static Object proxy(Object target){
        Object ret = Proxy.newProxyInstance(target.getClass().getClassLoader(),
            target.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.err.println("jdk动态代理之前....");
                    // 此处只能用target,则会去调用原方法
                    // 否则会死循环调用代理方法
                    Object res = method.invoke(target, args);
                    System.err.println("jdk动态代理之后....");
                    return res;
                }
            });
        return ret;
    }

}

package com.yl.job.task;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * @author Alex
 * @since 2018/10/9 13:28
 */
public class TaskMethodInterceptor implements MethodInterceptor {

    private static final Logger log = Logger.getLogger(TaskMethodInterceptor.class);

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        TaskComponet taskComponet = AnnotationUtils.findAnnotation(o.getClass(), TaskComponet.class);
        if(taskComponet == null){
            throw new RuntimeException("task not definit in ioc container");
        }
        String taskName = taskComponet.value();
        log.info("task:" + taskName + " 开始执行");
        // 此处进行任务运行拦截
        Object ret;
        try {
            ret = methodProxy.invokeSuper(o, args);// 原方法执行
        }catch (Exception e){
            log.error("task:" + taskName + " 执行异常", e);
            throw e;
        }finally {
            // 释放锁

        }
        log.info("task:" + taskName + " 执行结束");
        return ret;
    }
}

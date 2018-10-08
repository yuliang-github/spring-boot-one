package com.yl.job.task;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

/**
 * @author Alex
 * @since 2018/10/8 15:32
 */
public class TaskProxy {

    private static final Logger log = Logger.getLogger(TaskProxy.class);

    public static Task getProxy(String taskName, ApplicationContext context){
        final Task target = context.getBean(taskName, Task.class);
        Enhancer eh = new Enhancer();
        eh.setSuperclass(target.getClass());
        Callback[] callbacks = new Callback[]{(MethodInterceptor) (o, method, args, methodProxy) -> {
            log.info("task:" + taskName + " 开始执行");
            // 此处进行任务运行拦截
            Object ret;
            try {
                ret = methodProxy.invoke(o, args);// 原方法执行
            }catch (Exception e){
                log.error("task:" + taskName + " 执行异常", e);
                throw e;
            }finally {
                // 释放锁

            }
            log.info("task:" + taskName + " 执行结束");
            return ret;
        },NoOp.INSTANCE};
        eh.setCallbacks(callbacks);
        eh.setCallbackFilter(method -> {
            // getType方法使用第二个拦截器 即不拦截
            return method.getName().equals("getType")?1:0;
        });
        Object proxy = eh.create();
        return (Task) proxy;
    }

}

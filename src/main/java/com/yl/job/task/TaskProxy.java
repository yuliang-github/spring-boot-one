package com.yl.job.task;

import net.sf.cglib.proxy.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;

/**
 * @author Alex
 * @since 2018/10/8 15:32
 */
public class TaskProxy {

    private static final Logger log = Logger.getLogger(TaskProxy.class);

    public static Task getProxy(Task target, ApplicationContext context){
        Enhancer eh = new Enhancer();
        eh.setSuperclass(target.getClass());
        Callback[] callbacks = new Callback[]{(MethodInterceptor) (o, method, args, methodProxy) -> {
            TaskConstant.TaskType type = target.getType();
            log.info("task:" + target.getClass().getName() + " 开始执行");
            // 此处进行任务运行拦截
            Object ret = null;
            try {
                ret = methodProxy.invoke(o, args);// 原方法执行
            }catch (Exception e){
                log.error("task:" + target.getClass().getName() + " 执行异常", e);
                throw e;
            }finally {

            }
            log.info("task:" + target.getClass().getName() + " 执行结束");
            return ret;
        },NoOp.INSTANCE};
        eh.setCallbacks(callbacks);
        eh.setCallbackFilter(method -> {
            // getType方法使用第二个拦截器
            return method.getName().equals("getType")?1:0;
        });
        Object proxy = eh.create();
        return (Task) proxy;
    }

}

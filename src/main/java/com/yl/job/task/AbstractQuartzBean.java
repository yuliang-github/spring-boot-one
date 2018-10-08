package com.yl.job.task;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.ParameterizedType;

/**
 * @author Alex
 * @since 2018/9/29 13:57
 */
public abstract class AbstractQuartzBean<T extends Task> extends QuartzJobBean {

    private static Logger log = Logger.getLogger(AbstractQuartzBean.class);

    /**
     * 执行任务
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Class<T> taskClass  = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];
        TaskComponet taskComponet = AnnotationUtils.findAnnotation(taskClass, TaskComponet.class);
        ApplicationContext context = this.getContext(jobExecutionContext);
        this.executeTask(context, taskComponet);
    }

    /**
     * Spring的上下文对象会议key/value的形式存储在SchedulerContext中
     */
    private ApplicationContext getContext(JobExecutionContext jobContext){
        try {
            return (ApplicationContext) jobContext.getScheduler().getContext().get("applicationContextKey");
        }catch (Exception e){
            log.error(
                "jobexecutioncontext.getScheduler().getContext() error!", e);
            throw new RuntimeException(e);
        }
    }

    private void executeTask(ApplicationContext context,TaskComponet taskDefinit){
        if(taskDefinit == null){
            throw new RuntimeException("task object not definit in spring ioc container");
        }
        Task task = context.getBean(taskDefinit.value(), Task.class);
        // 代理执行
        TaskProxy.getProxy(task,context).run();
    }
}

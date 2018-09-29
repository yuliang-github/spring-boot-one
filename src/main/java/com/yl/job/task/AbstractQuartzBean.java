package com.yl.job.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.ParameterizedType;

/**
 * @author Alex
 * @since 2018/9/29 13:57
 */
public abstract class AbstractQuartzBean<T extends Task> extends QuartzJobBean {

    /**
     * 执行这种任务
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Class<T> taskClass  = (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];



    }
}

package com.yl.job.task;

import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @since 2018/9/30 15:27
 */
@Component
public @interface TaskComponet {

    String value() default "";

    boolean allowConcurrent() default false;


}

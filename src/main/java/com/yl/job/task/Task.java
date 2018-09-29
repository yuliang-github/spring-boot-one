package com.yl.job.task;

/**
 * @author Alex
 * @since 2018/9/29 13:58
 */
public interface Task {

    public void run();

    public TaskConstant.TaskType getType();

}

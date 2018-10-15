package com.yl.job.task;

/**
 * @author Alex
 * @since 2018/10/9 11:59
 */
@TaskComponet("demoTask")
public class DemoTask implements Task {

    @Override
    public void run() {
        System.err.println("demo task run...");
    }

    @Override
    public TaskConstant.TaskType getType() {
        return null;
    }
}

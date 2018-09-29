package com.yl.job.task;

/**
 * @author Alex
 * @since 2018/9/29 13:59
 */
public class TaskConstant {

    public static enum TaskType{

        CORE_TASK(1);// 核心任务

        int type;
        TaskType(int type){
            this.type = type;
        }
        public int get(){
            return this.type;
        }
    }

}

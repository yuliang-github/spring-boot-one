package com.yl.other.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Alex
 * @since 2018/10/24 14:38
 */
public class ThreadPool {

    private static ExecutorService es = Executors.newFixedThreadPool(5);

    public static void submitTask(Runnable runnable){

        es.submit(runnable);

    }


}

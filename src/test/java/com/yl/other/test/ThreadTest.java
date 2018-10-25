package com.yl.other.test;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author Alex
 * @since 2018/10/24 14:41
 */
public class ThreadTest {

    public static void main(String[] args) {

        ThreadPool.es.submit(new Runnable() {
            @Override
            public void run() {
              System.err.println("子线程进来了");
              int i = 1/0;
              System.err.println("子线程结束");
            }
        });
        System.err.println("----");
        Future<Void> future = ThreadPool.es.submit(new Callable<Void>() {

            @Override
            public Void call() throws Exception {
                System.err.println("子线程进来了");
                int i = 1 / 0;
                System.err.println("子线程结束");
                return null;
            }
        });
        try {
             future.get();
        } catch (Exception e){
            System.err.println(e);
        }
    }

}

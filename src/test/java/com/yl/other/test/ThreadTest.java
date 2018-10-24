package com.yl.other.test;

/**
 * @author Alex
 * @since 2018/10/24 14:41
 */
public class ThreadTest {

    public static void main(String[] args) {

        ThreadPool.submitTask(new Runnable() {
            @Override
            public void run() {
                System.err.println("子线程运行了");
            }
        });


    }

}

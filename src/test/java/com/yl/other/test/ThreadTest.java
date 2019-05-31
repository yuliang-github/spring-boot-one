package com.yl.other.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
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


    @Test
    public void demo() throws Exception{

        Thread t = new Thread(() -> {
            System.err.println("子线程进来了");
            try {
                Thread.sleep(10*1000);
            }catch (Exception e){

            }
            System.err.println("子线程结束了");
        });

        t.start();
        System.err.println("父线程开始等待");
        t.join();
        System.err.println("父线程等待结束");

        int i = 10_000;

    }

    @Test
    public void demo_4(){

        Map<Long,Integer> map = new HashMap<>();
        System.err.println(map.putIfAbsent(1L, 1));
        System.err.println(map.putIfAbsent(1L, 2));

    }
}

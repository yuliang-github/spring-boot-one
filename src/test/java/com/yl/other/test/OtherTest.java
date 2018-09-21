package com.yl.other.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Alex
 * @since 2018/9/5 11:14
 */
public class OtherTest {

    public static void main(String[] args) {

        String des = DemoBeanEx.Type.get(1).getDes();

        System.err.println(des);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        System.err.println(list);

        DemoBeanEx.syse();

        System.err.println(list);

        list.forEach(integer -> {
            System.err.println("V:" + integer);
        });

    }

    @Test
    public void demo_1() throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.err.println("子线程run...");
                    Thread.sleep(1000);
                    System.err.println("子线程end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        t.start();
        t.join();

        System.err.println("junit线程结束了");

    }

    @Test
    public void demo_3(){
        String companyName = "深圳市 阿拉50    互联网金融服务有限公司";

        String[] split = companyName.split("\\s+");

        System.err.println(Arrays.asList(split));

        System.err.println(Arrays.asList(companyName.split(" ")));
    }

}

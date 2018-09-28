package com.yl.other.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Thread t = new Thread(()->{
            try {
                System.err.println("子线程run...");
                Thread.sleep(1000);
                System.err.println("子线程end...");
            } catch (InterruptedException e) {
                e.printStackTrace();
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

    @Test
    public void demo_4() throws InterruptedException {
        Random random = new Random();
        while (true){
            System.err.println(random.nextInt(60));
            Thread.sleep(100);
        }
    }

    @Test
    public void demo_5() throws InterruptedException {
        DelayQueue queue = new DelayQueue();

        queue.offer(new DelayBean(1, 1000*30));

        queue.offer(new DelayBean(2, 1000*10));

        queue.offer(new DelayBean(3, 1000*20));

        while (true){
             /*
              * 每次取出一个元素,若无元素,或无到期元素 则阻塞
              */
            System.err.println(queue.take());
            System.err.println(queue);
        }
    }

    @Test
    public void demo_6(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(0);
        list.add(5);
        list.add(-1);
        list = list.parallelStream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer value) {
                return value.compareTo(0) <= 0;
            }
        }).collect(Collectors.toList());
        System.err.println(list);
        Stream<Integer> stream = list.parallelStream().filter(value ->
            value.compareTo(0) >= 0
        );
        System.err.println(stream.count());
    }

}

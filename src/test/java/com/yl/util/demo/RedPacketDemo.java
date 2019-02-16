package com.yl.util.demo;

import org.junit.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Alex
 * @since 2019/2/12 14:29
 */
public class RedPacketDemo {

    public static void main(String[] args) {

        //sendRedpacket(1000, 13);
        //System.err.println(rebutRedpacket(123));
        concurrentRebut(100);
    }

    private static void concurrentRebut(int num){
        ExecutorService es = Executors.newFixedThreadPool(num);
        CountDownLatch latch = new CountDownLatch(num);
        for(int i=0;i<num;i++){
            es.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                        System.err.println("线程:" + Thread.currentThread().getId() + "抢到了红包:" + rebutRedpacket(123));
                    }catch (Exception e){
                        System.err.println(e);
                    }
                }
            });
            latch.countDown();
        }
    }

    private static final int MIN_MONEY = 1;

    private static final int MAX_TIMES = 3;

    public static List<Integer> splitRedPacket(int money,int count){
        if(!check(money, count)){
            return null;
        }
        List<Integer> redpackets = new ArrayList<>(count);
        int max = (money / count)*MAX_TIMES;
        for(int i=0;i<count;i++){
            Integer one = randomOne(money, count - i,MIN_MONEY,max);
            redpackets.add(one);
            money -= one;
        }
        // 打乱
        Collections.shuffle(redpackets);
        return redpackets;
    }

    private static Integer randomOne(int leftMoney,int leftCount,int min,int max){
        Integer one = null;
        if(leftCount == 1){
            one = leftMoney;
        }else{
            int max_y = leftMoney - (leftCount - 1)*min;
            max = max > max_y?max_y:max;
            one = (int)Math.rint(Math.random()*(max - min) + min);
        }
        return one;
    }

    private static boolean check(int money,int count){
        boolean flag = true;
        if(money == 0 || count == 0){
            flag = false;
        }else{
            double avg = money / count;
            if(avg < MIN_MONEY){
                flag = false;
            }
        }
        return flag;
    }

    public static boolean  sendRedpacket(int money,int count){
        boolean flag = true;
        List<Integer> redPackets = splitRedPacket(money, count);
        if(redPackets == null || redPackets.isEmpty()){
            throw new RuntimeException("不符合红包分配规则");
        }
        RedissonClient client = RedissonUtil.getClient();
        RQueue<RedPacketItem> queue = client.getQueue("user:redpacket:123");
        int i = 1;
        for(Integer redpacket : redPackets){
            queue.offer(new RedPacketItem(i++, redpacket, 123));
        }
        return flag;
    }

    public static RedPacketItem rebutRedpacket(int rpId){
        RedissonClient client = RedissonUtil.getClient();
        RQueue<RedPacketItem> queue = client.getQueue("user:redpacket:" + rpId);
        return queue.poll();
    }

    @Test
    public void demo_1() throws Exception{
        RedissonClient client = RedissonUtil.getClient();

        RBucket<RedPacket> demo_set = client.getBucket("demo_set");

        int i = 0;

        while (i < 100){
            Thread.sleep(5*1000);
            RedPacket redPacket = demo_set.get();

            redPacket.addUser(i++);

            demo_set.set(redPacket);

        }
        client.shutdown();

    }

    @Test
    public void demo_2() throws Exception{
        RedissonClient client = RedissonUtil.getClient();

        RBucket<RedPacket> demo_set = client.getBucket("demo_set");

        int i = 0;
        while (i++ < 100){
            Thread.sleep(5*1000);
            System.err.println(demo_set.get());
        }
        client.shutdown();
    }


    @Test
    public void demo_3() throws Exception{
        RedissonClient client = RedissonUtil.getClient();

        RBucket<RedPacket> demo_set = client.getBucket("demo_set");

        RedPacket redPacket = new RedPacket(1, 50);
        redPacket.addUser(100);

        demo_set.set(redPacket, 30, TimeUnit.SECONDS);

        System.err.println("插入成功");

        client.shutdown();


    }

}

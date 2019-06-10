package com.yl.demo.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Alex
 * @since 2019/2/15 11:26
 */
public class ZkDemo {

    private static final Logger log = LoggerFactory.getLogger(ZkDemo.class);

    private static CuratorFramework CLIENT;

    static {
        CLIENT = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2181")
            .sessionTimeoutMs(10000)
            .connectionTimeoutMs(5000)
            .retryPolicy(new ExponentialBackoffRetry(3000, 3))
            .build();
        CLIENT.start();
    }

    public static void main(String[] args) throws Exception{



    }

    @Test
    public void demo_0() throws Exception{
        // 创建节点
        //CLIENT.create().forPath("/demo", "测试内容".getBytes());
        // 创建节点,若父节点不存在,则也创建父节点
        //CLIENT.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
        //   .forPath("/demo_one/data", "持久节点".getBytes());
        // 创建临时节点
        // 临时节点在程序结束时会删掉，但是不会删除父节点
        //CLIENT.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
        //  .forPath("/demo_linshi/data", "临时节点来一波".getBytes());

        List<String> nodes = CLIENT.getChildren().forPath("/dubbo");
        System.err.println(nodes);
        for (String node : nodes) {
            List<String> nodes_1 = CLIENT.getChildren().forPath("/dubbo/" + node);
            System.err.println(nodes_1);
            for (String s : nodes_1) {
                String nodePath = "/dubbo/" + node + "/" + s;
                List<String> nodes_2 = CLIENT.getChildren().forPath(nodePath);
                for (String s1 : nodes_2) {
                    String dataPath = nodePath + "/" + s1;
                    byte[] data = CLIENT.getData().forPath(dataPath);

                    System.err.println("节点:" + dataPath + ",内容:" + (data == null?"为空":new String(data)));

                }
            }
        }
        //Thread.sleep(30*1000);
    }

    @Test
    public void demo_1()throws Exception{

        NodeCache nodeCache = new NodeCache(CLIENT,"/demo_one",false);

        // 只能监听节点数据及节点本身的变化,不能感知子节点的变化
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                ChildData childData = nodeCache.getCurrentData();
                log.info("ZNode节点状态改变, path={}", childData.getPath());
                log.info("ZNode节点状态改变, data={}", new String(childData.getData(), "Utf-8"));
                log.info("ZNode节点状态改变, stat={}", childData.getStat());
            }
        });
        nodeCache.start();

        Thread.sleep(1000*10*60);
    }

    @Test
    public void demo_2()throws Exception{

        // 第三个参数,是否缓存子节点数据
        PathChildrenCache pathChildrenCache = new PathChildrenCache(CLIENT, "/zk/count",true);

        // 只能监听子节点数据变化
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                ChildData data = pathChildrenCacheEvent.getData();
                switch (pathChildrenCacheEvent.getType()) {
                    case CHILD_ADDED:
                        log.info("子节点增加, path={}, data={}",
                            data.getPath(), new String(data.getData(), "UTF-8"));
                        break;
                    case CHILD_UPDATED:
                        log.info("子节点更新, path={}, data={}",
                            data.getPath(), new String(data.getData(), "UTF-8"));
                        break;
                    case CHILD_REMOVED:
                        log.info("子节点删除, path={}, data={}",
                            data.getPath(), new String(data.getData(), "UTF-8"));
                        break;
                    default:
                        log.info("事件类型不明显");
                        break;
                }
            }
        });
        pathChildrenCache.start();

        Thread.sleep(1000*10*60);
    }

    @Test
    public void demo_3() throws Exception{
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
               try {
                   DistributedAtomicLong count = new DistributedAtomicLong(CLIENT, "/zk/count",
                       new RetryNTimes(10, 100));
                   while (true){
                       AtomicValue<Long> increment = count.increment();
                       System.err.println("pre:" + increment.preValue());
                       System.err.println("post:" + increment.postValue());
                       Thread.currentThread().sleep(5000);
                   }
               }catch (Exception e){

               }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DistributedAtomicLong count = new DistributedAtomicLong(CLIENT, "/zk/count",
                        new RetryNTimes(10, 100));
                    while (true){
                        AtomicValue<Long> increment = count.increment();
                        System.err.println("pre:" + increment.preValue());
                        System.err.println("post:" + increment.postValue());
                        Thread.currentThread().sleep(5000);
                    }
                }catch (Exception e){

                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}

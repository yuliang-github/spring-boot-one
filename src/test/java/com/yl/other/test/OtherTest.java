package com.yl.other.test;

import com.yl.common.bean.UserBasicBean;
import com.yl.common.mapper.UserBasicBeanMapper;
import com.yl.job.task.DemoTask;
import com.yl.job.task.JdkProxy;
import com.yl.job.task.Task;
import com.yl.springboot.config.MyBatisConfig;
import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
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

    @Test
    public void demo_7(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("a");

        List<String> newList = list.stream().map(e -> e.toUpperCase()).collect(Collectors.toList());
        System.err.println(list);
        System.err.println(newList);

        List<String> sortedList = newList.stream().distinct().sorted(String::compareTo).collect(Collectors.toList());
        System.err.println(list);
        System.err.println(sortedList);

    }

    @Test
    public void demo_8(){
        DemoTask target = new DemoTask();
        Task task = (Task) JdkProxy.proxy(target);
        task.getType();
    }

    @Test
    public void demo_9(){
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);
        list.add(3);list.add(4);
        list.add(5);list.add(6);

//        for (Integer i:list){
//            if(3 == i){
//                list.remove(i);
//            }
//            System.err.println(list);
//        }

//        for (int i = 0;i < list.size();i++){
//            Integer e = list.get(i);
//            if(e == 3){
//                list.remove(e);
//            }
//            System.err.println(list);
//        }

        Iterator<Integer> iterator = list.iterator();
        List<Integer> newList = new ArrayList<>();
        while(iterator.hasNext()){
            Integer e = iterator.next();
            if(e == 3){
                iterator.remove();
            }
            if(e == 3){
                newList.add(e);
            }
            System.err.println(list);

            System.err.println(newList);
        }

    }

    @Test
    public void demo_10() throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);

        DataSource dataSource = context.getBean(DataSource.class);

        System.err.println(1 + ":" + dataSource);

        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("dev", transactionFactory, dataSource);

        Configuration configuration = new Configuration(environment);

        configuration.addMapper(UserBasicBeanMapper.class);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        SqlSession session = sessionFactory.openSession();

        UserBasicBeanMapper mapper = session.getMapper(UserBasicBeanMapper.class);

        UserBasicBean user = mapper.get(1);

        System.err.println(user);

        session.close();
    }

    @Test
    public void demo_11(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        SqlSessionFactoryBean sqlSessionFactoryBean = context.getBean(SqlSessionFactoryBean.class);
        System.err.println(sqlSessionFactoryBean);
        UserBasicBeanMapper mapper = context.getBean(UserBasicBeanMapper.class);
        UserBasicBean user = mapper.get(1);
        System.err.println(user);
    }

}

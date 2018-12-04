package com.yl.other.test;

import com.alibaba.fastjson.JSONObject;
import com.yl.common.bean.UserBasicBean;
import com.yl.common.demo.User;
import com.yl.common.mapper.UserBasicBeanMapper;
import com.yl.demo.bean.DemoBean;
import com.yl.job.task.DemoTask;
import com.yl.job.task.JdkProxy;
import com.yl.job.task.Task;
import com.yl.springboot.config.MyBatisConfig;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;
import org.mvel2.MVEL;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
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
        int i = 4;
        while (true){
            /*
             * 每次取出一个元素,若无元素,或无到期元素 则阻塞
             */
            System.err.println(queue);
            System.err.println(queue.take());
            queue.offer(new DelayBean(i, 10000*i++));
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

        UserBasicBean user = mapper.get(2);

        System.err.println(user);

        session.close();
    }

    @Test
    public void demo_11(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);
        UserBasicBeanMapper mapper = context.getBean(UserBasicBeanMapper.class);
        UserBasicBean user = mapper.get(2);
        System.err.println(user);
    }

    @Test
    public void demo_12(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(6);
        list.add(4);
        list.add(3);

        System.err.println(list);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2 >= 0 ? -1:1;
            }
        });

        System.err.println(list);

    }

    @Test
    public void demo_13(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(6);
        list.add(4);
        list.add(3);

        for (Integer i : list) {
            if(i > 4){
                list.add(10);
            }
            System.err.println(i);
        }

        System.err.println(list.indexOf(2));
    }

    @Test
    public void demo_14() throws InvocationTargetException, IllegalAccessException, IOException {
        ObjectFactory objectFactory = new DefaultObjectFactory();

        User user = objectFactory.create(User.class);

        System.err.println(user);

        Reflector reflector = new Reflector(user.getClass());

        Invoker invoker = reflector.getSetInvoker("name");

        invoker.invoke(user, new Object[]{"北京"});

        System.err.println(user);

    }

    @Test
    public void demo_15(){
        DemoBean demoBean = new DemoBean();

        demoBean.setId(1);
        demoBean.setStatus(DemoBean.Status.ALL_CRASHED);

        System.err.println(JSONObject.toJSONString(demoBean));
    }


    @Test
    public void demo_16() throws Exception{

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
        prop.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(prop);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress("yu.alex@51onion.com"));

        message.addRecipient(Message.RecipientType.TO,new InternetAddress("chenzhiling@mintq.com"));

        message.setSubject("带附件的测试邮件", "UTF-8");

        Multipart mp = new MimeMultipart();

        MimeBodyPart part_text = new MimeBodyPart();
        part_text.setContent("测试(带附件)!", "text/html;charset=UTF-8" );
        mp.addBodyPart(part_text);

       MimeBodyPart part_file = new MimeBodyPart();
        DataHandler fileHandler = new DataHandler(new FileDataSource("/Users/alex/Public/auto.xlsx"));
        part_file.setDataHandler(fileHandler);
        part_file.setFileName(MimeUtility.encodeText(fileHandler.getName()));
        part_file.setFileName(MimeUtility.encodeText("测试附件.xlsx"));
        mp.addBodyPart(part_file);

        message.setContent(mp);

        message.setSentDate(new Date());

        message.saveChanges();

        Transport transport = session.getTransport("smtp");

        transport.connect("yu.alex@51onion.com", "Yl33512660716");

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();


    }


    @Test
    public void demo_17() throws IOException {

        File file = new File("/Users/alex/Public/temps/demo.txt");

        PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));

        pw.append("天谕神座");

        pw.flush();


    }

    @Test
    public void demo_18(){
        Map<Integer,User> userMap = new HashMap<>();

        User user = new User(1, "miss");

        userMap.put(1, user);

        System.err.println(userMap);

        user.setId(2);

        System.err.println(userMap);

        User user_1 = userMap.get(1);

        System.err.println(user == user_1);

        user_1.setId(3);
        user_1.setName("jack");

        System.err.println(userMap);

        System.err.println(user);
    }

    @Test
    public void  demo_19(){
        Integer[] ints = new Integer[]{3,1,4,2};

        Arrays.sort(ints, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        //System.err.println(Arrays.asList(ints));

       //System.out.println(Arrays.asList(ints).indexOf(2));

        byte[] decode = Base64.getDecoder().decode("6YeR5Yqg5a6H");
        System.err.println(new String(decode));

    }

    @Test
    public void demo_20(){

        String mvel = "a > b";

        Map<String,Object> params = new HashMap<>();
        params.put("a", 0.124564321);
        params.put("b", 2.32113456);

        System.err.println(MVEL.evalToBoolean(mvel, params));
    }




}

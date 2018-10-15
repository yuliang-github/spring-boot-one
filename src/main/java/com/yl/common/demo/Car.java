package com.yl.common.demo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author yu.alex
 * @Create 2018/9/1 18:17
 */
public class Car implements InitializingBean, DisposableBean {

    @Autowired
    private User user;

    public Car(){
        System.err.println("IOC容器创建Car");
    }

    public void init_1(){
        System.err.println("@Bean策略初始化Car:" + name);
    }
    public void destroy_1(){
        System.err.println("@Bean策略销毁Car:" + name);
    }

    @PostConstruct
    public void init_2(){
        System.err.println("@PostConstruct策略初始化Car:" + name);
    }
    @PreDestroy
    public void destroy_2(){
        System.err.println("@PreDestroy策略销毁Car:" + name);
    }


    @Override
    public void destroy() throws Exception {
        System.err.println("DisposableBean策略销毁Car:" + name);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("InitializingBean策略初始化Car:" + name);
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "user=" + user +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.yl.common.demo;

/**
 * @author Alex
 * @since 2018/8/29 15:23
 */
public class Service {

    public Service(){
        System.err.println("无参构造方法创建service");
    }

    @Override
    public String toString() {
        return "Service{使用import方法创建bean演示}";
    }
}

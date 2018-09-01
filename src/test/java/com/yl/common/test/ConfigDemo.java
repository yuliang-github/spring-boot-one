package com.yl.common.test;

import com.taobao.diamond.manager.DiamondManager;
import com.taobao.diamond.manager.ManagerListener;
import com.taobao.diamond.manager.impl.DefaultDiamondManager;
import com.yl.common.controller.IndexController;
import com.yl.common.demo.config.MainConfigLifeCycle;
import com.yl.common.utils.PackageSanner;
import com.yl.springboot.config.BeanCreateConfig;
import com.yl.springboot.config.ConponentScanConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author Alex
 * @since 2018/8/28 16:26
 */
public class ConfigDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConponentScanConfig.class);

        IndexController bean = context.getBean(IndexController.class);

        System.err.println(bean.index());

        System.out.println(bean.index());
    }

    @Test
    public void demo_1(){
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanCreateConfig.class);

        System.err.println("ioc容器初始化完成");

        Map<String, Object> beans = context.getBeansOfType(Object.class);

        beans.forEach((k,v)->{
            System.err.println("beanName:" + k +",bean:" + v);
        });
    }

    @Test
    public void demo_2(){

        DiamondManager diamondManager = new DefaultDiamondManager("market", "service",
            new ManagerListener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {

                }
            });

        String info = diamondManager.getAvailableConfigureInfomation(100);
        System.err.println(info);

    }

    @Test
    public void demo_3(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfigLifeCycle.class);



    }
}

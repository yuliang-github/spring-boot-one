package com.yl.common.test;

import com.yl.common.controller.IndexController;
import com.yl.springboot.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Alex
 * @since 2018/8/28 16:26
 */
public class ConfigDemo {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        IndexController bean = context.getBean(IndexController.class);

        System.err.println(bean.index());

        System.out.println(bean.index());
    }

}

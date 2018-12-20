package com.yl.mybatis.test;

import com.yl.common.service.UserService;
import com.yl.springboot.config.MyBatisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Alex
 * @since 2018/11/5 09:58
 */
public class MybatisDemo {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyBatisConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.updateName(2, "北京啊北京");

    }

}

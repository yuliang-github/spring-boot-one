package com.yl.springboot.config;

import com.yl.common.demo.MathCalculator;
import com.yl.common.demo.config.LogAspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author yu.alex
 * @Create 2018/9/13 20:42
 */
@Configuration
// 启动基于注解的Aop模式
@EnableAspectJAutoProxy
public class AopConfig {

    /**
     * spring AOP
     *      1.原理：动态代理
     *      2.导入spring-aop,spring-aspects
     *
     * 通知类型
     *      1.前置通知
     *
     */


    @Bean("mathCal")
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}

package com.yl.springboot.config;

import com.yl.common.demo.Service;
import com.yl.common.demo.User;
import com.yl.common.demo.config.MacConditional;
import com.yl.common.demo.config.MicrosoftConditional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Alex
 * @since 2018/8/29 14:22
 */
@Configuration
@Import(value = {Service.class})
public class BeanCreateConfig {

    /**
     * spring创建bean的几种方式
     *      1.使用spring自带的注解@Controller并进行扫描,易可自定义拓展注解使用@Conponent作为元注解
     *      2.@Bean注解创建bean
     *      3.@Import(value = {Service.class})注册组件
     *          3.1 该组件必须有无参构造方法
     */

    /*
     * 1.默认bean的名字同方法名
     * 2.@Scope指定bean是否单例还是多例
     *      2.1 "prototype"多例,ioc容器启动时不会创建,在获取时才创建
     *          每获取一次创建一次
     *      2.2 "singleton"单例,默认值,在ioc容器启动时创建
     * 3.@Conditional指定bean创建时需要满足的条件
     *      3.1 可自定义Condition
     *      3.2 当配置多个Condition,只有满足所有Condition时,才会创建Bean
     * 4.ImportSelector的使用
     */
    @Bean(value = "user_default")
    //@Scope("prototype")
    public User user(){
        System.err.println("user_default开始实例化");
        return new User(1,"miss");
    }

    @Bean(value = "user_microsoft")
    @Conditional(value = {MicrosoftConditional.class})
    public User user_ms(){
        return new User(2,"微软");
    }
    @Bean(value = "user_mac")
    @Conditional(value = {MacConditional.class,MicrosoftConditional.class})
    public User user_lx(){
        return new User(2,"mac");
    }

}
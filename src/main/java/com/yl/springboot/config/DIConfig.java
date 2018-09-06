package com.yl.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Alex
 * @since 2018/9/6 11:27
 *      依赖注入
 *
 */
@Configuration
@ComponentScan(value = {"com.yl.common"})
public class DIConfig {

    /**
     *  依赖注入的几种方式
     *      1.@Autowired根据类型注入,多个实例根据属性名
     *          1.1 @Autowired(required = false)如不配置required=false,未注入会抛异常
     *          1.2 @Pramary注解,标识首选注入,如未指定BeanName,默认注入@Primary标注的Bean
     *      2.@Autowired与@Qualifier("userDao")配合、根据类型与beanName注入
     *      3.@Resource注入,不支持@Primary注解
     *      4.@Inject注入,支持@Primary注解
     */

}



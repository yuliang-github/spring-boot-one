package com.yl.springboot.config;

import com.yl.common.demo.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Alex
 * @since 2018/9/6 10:00
 *      赋值
 */
@Configuration
@Import(value = {Person.class})
@PropertySource(value = {"classpath:tmp/demo.properties"},encoding = "UTF-8")
//@PropertySources(
//    @PropertySource(value = {"classpath:tmp/demo.properties"},encoding = "UTF-8")
//)
public class ValueProfileConfig {

    /**
     * 关于Bean赋值
     *      1.@Value赋值
     *          1.1 直接赋值@Value("miss")
     *          1.2 SPEL赋值@Value("#{1+2}")
     *          1.3 外部文件赋值
     *              1.3.1 @PropertySource注解引入配置文件
     *              1.3.2 encoding指定编码,解决中文乱码
     *              1.3.3 @Value("${person.name}")取外部文件的值
     *              1.3.4 拓展(Spring PropertySource加载的配置文件会注入到Environment之中)
     *                      Environment environment = context.getEnvironment();
     *                      environment.getProperty("person.name")
     *
     * 关于外部配置文件加载
     *      1.@PropertySource加载,value可配置多个文件,encoding指定编码
     *          1.1 不可使用通配符加载配置文件
     *      2.@PropertySources配置多个@PropertySource
     */



}

package com.yl.common.demo.config;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Alex
 * @since 2018/8/29 14:51
 */
public class MicrosoftConditional implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取Spring的beanFactory,可以获取已经创建的所有Bean
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();

        // 获取Spring的BeanDefinition注册中心,可以获取所有已创建的BeanDefinition
        BeanDefinitionRegistry registry = context.getRegistry();

        // 获取类加载器
        ClassLoader classLoader = context.getClassLoader();

        // 获取JAVA 虚拟机运行环境 可以获取JVM环境变量
        Environment environment = context.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.err.println("本机环境osName:" + osName);
        return osName.contains("Windows");
    }
}

package com.yl.common.demo.config;

import com.yl.common.demo.Blue;
import com.yl.common.demo.Red;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Alex
 * @since 2018/8/30 10:23
 */
public class CustomeImportBeanDefinitionRegisty implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        /**
         * BeanDefinitionRegistry beanDefinition的注册中心
         *      1.可以获取任何已注册的beanDefinition
         */
        if (registry.containsBeanDefinition(Red.class.getName())){
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Blue.class);
            registry.registerBeanDefinition("blue",beanDefinition);
        }
    }
}

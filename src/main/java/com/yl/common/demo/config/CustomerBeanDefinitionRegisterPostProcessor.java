package com.yl.common.demo.config;

import com.yl.common.service.impl.UserServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author Alex
 * @since 2018/9/17 17:37
 */
public class CustomerBeanDefinitionRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.err.println("注册bean");
        AbstractBeanDefinition definition = BeanDefinitionBuilder.rootBeanDefinition(UserServiceImpl.class).getBeanDefinition();
        registry.registerBeanDefinition("userService", definition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println("CustomerBeanDefinitionRegisterPostProcessor beanDefinition数量:" + beanFactory.getBeanDefinitionCount());
    }

}

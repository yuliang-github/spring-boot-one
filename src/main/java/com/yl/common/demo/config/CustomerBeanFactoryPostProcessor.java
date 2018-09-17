package com.yl.common.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Alex
 * @since 2018/9/17 17:33
 */
public class CustomerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        // 获取bean的定义信息
        System.err.println("CustomerBeanFactoryPostProcessor beanDefinition数量:" + beanDefinitionCount);
    }

}

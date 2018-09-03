package com.yl.common.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Alex
 * @since 2018/9/3 14:27
 */
public class CustomerBeanPocessor implements BeanPostProcessor {

    /**
     * 此方法在Bean初始化之前调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("CustomerBeanPocessor.postProcessBeforeInitialization方法,beanName:" + beanName);
        return bean;
    }

    /**
     * 此方法在Bean初始化方法调用之后调用
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("CustomerBeanPocessor.postProcessAfterInitialization,beanName:" + beanName);
        return bean;
    }
}

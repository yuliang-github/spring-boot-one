package com.yl.common.demo.config;

import com.yl.common.service.UserService;
import com.yl.common.service.impl.UserServiceImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author yu.alex
 * @Create 2018/9/1 17:25
 */
public class CustomerFactoryBean implements FactoryBean<UserService> {

    @Override
    public UserService getObject() {
        return new UserServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }

    // 是否是单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}

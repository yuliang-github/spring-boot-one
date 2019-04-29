package com.yl.common.service.impl;

import com.yl.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Alex
 * @since 2019/4/29 16:18
 */
public abstract class Service<T extends UserService> {

    @Autowired
    protected T t;

    public void print(){
        System.err.println(t.getClass().getName());
    }
}

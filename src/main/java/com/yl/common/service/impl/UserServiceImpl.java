package com.yl.common.service.impl;

import com.yl.common.demo.User;
import com.yl.common.demo.UserDao;
import com.yl.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Alex
 * @since 2018/8/28 16:10
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    @Qualifier("userDao")
    //@Resource
    private UserDao userDao;

    @Override
    public User get(int id) {
        return new User(id, "miss");
    }

    @Override
    public void del(int id) {
        System.err.println("del user:" + id);
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
            "userDao=" + userDao +
            '}';
    }
}

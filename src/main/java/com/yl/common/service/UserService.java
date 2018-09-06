package com.yl.common.service;

import com.yl.common.demo.User;

/**
 * @author Alex
 * @since 2018/8/28 16:09
 */
public interface UserService {

    public User get(int id);

    public void del(int id);

}

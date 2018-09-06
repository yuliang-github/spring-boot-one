package com.yl.common.service;

import com.yl.common.demo.User;

/**
 * @author Alex
 * @since 2018/8/28 16:09
 */
public interface UserService {

    User get(int id);

    void del(int id);

}

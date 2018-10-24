package com.yl.common.reflect;

import com.yl.common.demo.User;

import java.util.List;

/**
 * @author Alex
 * @since 2018/10/24 14:55
 */
public interface UserMapperEx extends UserMapper{

    List<User> getAll();

}

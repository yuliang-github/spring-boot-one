package com.yl.common.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * @author Alex
 * @since 2018/9/6 11:32
 */


@Repository("userDao")
public class UserDao {

    @Value("default_user_dao")
    public String label;

    @Override
    public String toString() {
        return "UserDao{" +
            "label='" + label + '\'' +
            '}';
    }
}

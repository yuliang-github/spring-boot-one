package com.yl.common.service;

import com.yl.common.demo.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alex
 * @since 2018/8/28 16:09
 */
@Transactional
public interface UserService {

    User get(int id);

    default void del(int id){

    }

    default int updateName(int id,String name){
        return 0;
    }
}

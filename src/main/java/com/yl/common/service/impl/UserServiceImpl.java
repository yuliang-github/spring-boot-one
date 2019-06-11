package com.yl.common.service.impl;

import com.yl.common.bean.UserBasicBean;
import com.yl.common.demo.User;
import com.yl.common.mapper.UserBasicBeanMapper;
import com.yl.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alex
 * @since 2018/8/28 16:10
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBasicBeanMapper userBasicBeanMapper;

    @Override
    public User get(int id) {
        return new User(id, "miss");
    }

    @Override
    public void del(int id) {
        System.err.println("del user:" + id);
    }

    @Override
    //@Transactional
    public int updateName(int id, String name) {
        UserBasicBean user = userBasicBeanMapper.get(id);

        System.err.println(user);

        int ret = userBasicBeanMapper.updateName(id, name);
        System.err.println(ret);

        System.err.println(user);
        return 0;
    }

    //@Transactional
    public void update(int id,String names){
        int ret = userBasicBeanMapper.updateName(id, names);
        System.err.println(ret);
        if(id % 2 == 0){
            throw new RuntimeException("tx exception");
        }
    }
}

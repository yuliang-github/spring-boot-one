package com.yl.common.service.impl;

import com.yl.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @since 2019/4/29 16:20
 */
@Component
public class ImplService extends Service<UserServiceImpl>{

    @Autowired
    private UserService userService;

    public ImplService(){
        System.err.println("init ImplService");
    }
}

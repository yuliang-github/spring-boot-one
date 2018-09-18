package com.yl.common.demo;

import org.springframework.context.ApplicationEvent;

/**
 * @author Alex
 * @since 2018/9/17 16:57
 */
public class UserEvent extends ApplicationEvent {

    public UserEvent(User user) {
        super(user);
    }
}

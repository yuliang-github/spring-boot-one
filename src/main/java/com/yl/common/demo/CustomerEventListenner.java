package com.yl.common.demo;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @since 2018/9/17 16:56
 */
@Component
public class CustomerEventListenner {

    @EventListener(value = {UserEvent.class})
    @Async
    public void userListenner(UserEvent userEvent){

        System.err.println("当前线程:" + Thread.currentThread().getName());

        System.err.println("收到用户事件:" + userEvent.getSource());

    }

}

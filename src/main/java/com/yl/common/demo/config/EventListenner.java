package com.yl.common.demo.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @since 2018/9/17 14:39
 */
@Component
public class EventListenner implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.err.println("监听spring事件:" + event.getSource());
    }
}

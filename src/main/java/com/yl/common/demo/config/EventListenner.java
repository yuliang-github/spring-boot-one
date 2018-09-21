package com.yl.common.demo.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author Alex
 * @since 2018/9/17 14:39
 */
@Component
public class EventListenner implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        System.err.println("监听spring事件:" + event.getSource());
        if(event instanceof ServletRequestHandledEvent){
            ServletRequestHandledEvent servletEvent = (ServletRequestHandledEvent) event;
            String requestUrl = servletEvent.getRequestUrl();
            System.err.println("请求URL:" + requestUrl);
        }
    }
}

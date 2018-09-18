package com.yl.common.demo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @since 2018/9/17 17:07
 */
@Component
public class EventPublisher implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        EventPublisher.publisher = applicationEventPublisher;
    }

    public static void publishEvent(ApplicationEvent event){
        EventPublisher.publisher.publishEvent(event);
    }
}

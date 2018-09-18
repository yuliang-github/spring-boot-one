package com.yl.springboot.config;

import com.yl.common.demo.CustomerEventListenner;
import com.yl.common.demo.EventPublisher;
import com.yl.common.demo.UserDao;
import com.yl.common.demo.config.CustomerBeanFactoryPostProcessor;
import com.yl.common.demo.config.CustomerBeanDefinitionRegisterPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Alex
 * @since 2018/9/17 14:48
 */
@Configuration
@Import(value = {CustomerEventListenner.class, EventPublisher.class, CustomerBeanFactoryPostProcessor.class,
    CustomerBeanDefinitionRegisterPostProcessor.class, UserDao.class})
public class EventConfig {

    /**
     *  spring事件驱动编程
     *      1.ContextRefreshedEvent:在spring容器初始化结束后调用
     *      2.ContextClosedEvent:在spring容器关闭时调用
     *
     *  自定义spring事件驱动编程
     *      1.注册组件事件派发器到IOC容器
     *          1.1 注入ThreadPoolTaskExecutor,若未注入,则不能异步监听事件,只是同步
     *      2.注册事件监听器到IOC容器
     *          2.1 方式一:继承ApplicationListener<ApplicationEvent>
     *          2.2 方式二:注解
     *          @EventListener(value = {UserEvent.class})
     *          @Async
     *          public void userListenner(UserEvent userEvent){
     *              System.err.println("收到用户事件:" + userEvent.getSource());
     *          }
     *      3.事件发布
     *          3.1 ApplicationContext发布context.publishEvent(new ApplicationEvent("自定义事件") {});
     *          3.2 实现ApplicationEventPublisherAware接口注入ApplicationEventPublisher
     *              然后ApplicationEventPublisher.publishEvent(event);
     */

    @Bean
    public ThreadPoolTaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);// 核心线程池大小
        executor.setMaxPoolSize(20);// 最大线程池大小
        executor.setQueueCapacity(100);// 工作队列大小
        executor.setKeepAliveSeconds(60);// 线程活跃秒数
        executor.setThreadNamePrefix("spring-event-task-");// 线程名前缀
        return executor;
    }

    /**
     * 只有配置了异步事件派发器,才能是真正的异步执行
     * 否则只是同步
     */
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(){
        ApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        ((SimpleApplicationEventMulticaster) applicationEventMulticaster).setTaskExecutor(taskExecutor());
        return applicationEventMulticaster;
    }


}

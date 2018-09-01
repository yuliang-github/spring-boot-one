package com.yl.common.demo.config;

import com.yl.common.demo.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yu.alex
 * @Create 2018/9/1 17:55
 *
 *
 *
 * Bean的生命周期:bean创建->初始化->bean销毁
 *      我们可以自定义bean的初始化和销毁方法,容器在bean进行到当前生命周期的时候自动调用我们定义的方法
 *
 */
@Configuration
public class MainConfigLifeCycle {

    @Bean
    public Car car(){
        return new Car();
    }

}

package com.yl.springboot.config;

import com.yl.common.demo.Car;
import com.yl.common.demo.User;
import com.yl.common.demo.config.CustomerBeanPocessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

/**
 * @author Alex
 * @since 2018/9/3 11:26
 * Bean的生命周期:bean创建->初始化->bean销毁
 *      我们可以自定义bean的初始化和销毁方法,容器在bean进行到当前生命周期的时候自动调用我们定义的方法
 */
@Configuration
@Import(value = {CustomerBeanPocessor.class})
public class BeanLifeCycleConfig {

    /**
     * 自定义Bean生命周期的策略(一)
     *      1.initMethod在Bean创建完成后调用
     *      2.destroyMethod在IOC容器关闭的时候执行(但是仅限单例)
     * 自定义Bean生命周期的策略(二)
     *      1.@PostConstruct在Bean创建完成后调用
     *      2.@PreDestroy在IOC容易关闭的时候执行但是仅限单例)
     * 自定义Bean生命周期的策略(三)
     *      1.实现InitializingBean接口,进行初始化工作
     *      2.实现DisposableBean,在IOC容器关闭时执行销毁工作(但是仅限单例)
     *
     * BeanPostProcessor接口
     *      1.实现BeanPostProcessor,并注入IOC容器
     *          1.1 postProcessBeforeInitialization方法在Bean初始化方法之前调用
     *          1.2 postProcessAfterInitialization方法在Bean初始化方法之后调用
     *
     *
     */
    @Bean(initMethod = "init_1",destroyMethod = "destroy_1")
    @Scope("singleton")
    public Car car(){
        Car car = new Car();
        car.setName("benz");
        return car;
    }

    @Bean
    public User user(){
        return new User(1, "miss");
    }
}

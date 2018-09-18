package com.yl.springboot.config;

import com.yl.common.demo.MathCalculator;
import com.yl.common.demo.config.LogAspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author yu.alex
 * @Create 2018/9/13 20:42
 */
@Configuration
// 启动基于注解的Aop模式
@EnableAspectJAutoProxy
public class AopConfig {

    /**
     * spring AOP
     *      1.原理：动态代理
     *      2.导入spring-aop,spring-aspects
     *
     * 如何开启
     *      1.配置类中加入@EnableAspectJAutoProxy注解,标识启动切面注解
     *      2.切面类加入注解@Aspect,标识这是一个切面类
     *      3.将切面类加入spring容器
     *      4.定义切点,栗子:
     *          @Pointcut("execution(* com.yl.common.demo.MathCalculator.*(..))")
     *          public void pointCut(){}
     *      5.定义通知,栗子:@After(value = "pointCut()")
     *
     *
     * 通知类型
     *      1.前置通知@Before
     *          1.1 可带JoinPoint参数,获取方法参数等信息
     *          1.2 在方法调用前进行拦截
     *      2.后置通知@After
     *          2.1 可带JoinPoint参数,获取方法参数等信息
     *          2.2 在方法结束后拦截,无论方法是否正常结束(包括发生异常)
     *      3.返回通知@AfterReturning
     *          3.1 可带JoinPoint jp,Object ret
     *              3.1.1 JoinPoint可获取方法参数等信息,ret为方法返回参数
     *              3.1.2 注意:JoinPoint参数必须在ret参数之前
     *          3.2 在方法返回值之后调用,方法发生异常不会拦截
     *      4.异常通知@AfterThrowing
     *          4.1 可带参数JoinPoint jp,Exception e
     *              4.1.1 jp可获取方法参数等信息,e为发生的异常
     *              4.1.2 注意:JointPoint参数必须在e之前
     *              4.1.3 注意:若环绕通知中捕获了异常并未抛出,则不会拦截
     *          4.2 在方法抛出异常时拦截
     *      5.环绕通知:@Around
     *          5.1 必须携带参数ProceedingJoinPoint pjp
     *              5.1.1 pjp参数可获取方法参数等信息
     *              5.1.2 pjp.proceed()方法为调用目标方法,可决定是否执行目标方法
     *          5.2 必须有返回值,且返回值与目标方法应保持一致
     *          5.3 可在方法执行前后进行拦截
     *
     */


    @Bean("mathCal")
    public MathCalculator mathCalculator(){
        return new MathCalculator();
    }

    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }

}

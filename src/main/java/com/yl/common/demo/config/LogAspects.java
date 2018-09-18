package com.yl.common.demo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Author yu.alex
 * @Create 2018/9/13 20:46
 */
// @Aspect告诉spring,当前类是一个切面类
@Aspect
public class LogAspects {


    /**
     * 切点表达式
     */
    @Pointcut("execution(* com.yl.common.demo.MathCalculator.*(..))")
    public void pointCut(){}

    /**
     * 前置通知,在方法运行前拦截
     * @param jp
     */
    @Before("pointCut()")
    public void before(JoinPoint jp){
        Object[] parms = jp.getArgs();
//        System.err.println(jp.getTarget().getClass());
        System.out.println(jp.getSignature().getName() + "开始,参数{"+ Arrays.asList(parms) +"}");
    }

    /**
     * 后置通知,在方法结束之后拦截
     * 方法发生异常,也会调用
     * @param jp
     */
    @After(value = "pointCut()")
    public void after(JoinPoint jp){
        Object[] parms = jp.getArgs();
        System.out.println(jp.getSignature().getName() + "结束,参数{"+ Arrays.asList(parms) +"}");
    }

    /**
     * 返回通知,在方法正常结束,返回值之后拦截
     * 注意:JoinPoint jp,Object ret参数顺序不能变
     * @param jp
     * @param ret
     */
    @AfterReturning(value = "pointCut()",returning = "ret")
    public void afterReturn(JoinPoint jp,Object ret){
        Object[] parms = jp.getArgs();
        System.out.println(jp.getSignature().getName() + "返回"+ret+",参数{"+ Arrays.asList(parms) +"}");
    }

    /**
     * 异常通知,在方法发生异常时拦截
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "pointCut()",throwing = "e")
    public void afterException(JoinPoint jp,Exception e){
        Object[] parms = jp.getArgs();
        System.out.println(jp.getSignature().getName() + "发生异常"+e+",参数{"+ Arrays.asList(parms) +"}");
    }

    /**
     * 环绕通知
     * 环绕通知需要携带ProceedingJoinPoint 类型参数
     * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即为目标方法的返回值
     * 若发生异常,不抛出的话,AfterThrowing则不会拦截
     * 返回值的类型需要和方法类型保持一致
     */
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint pjp){
        Object ret = 0;
        Object[] parms = pjp.getArgs();
        try {
            System.out.println(pjp.getSignature().getName() + "环绕通知前,参数{"+ Arrays.asList(parms) +"}");
            ret = pjp.proceed(parms);
            System.out.println(pjp.getSignature().getName() + "环绕通知后,返回值{"+ret+"},参数{"+ Arrays.asList(parms) +"}");
        }catch (Throwable e){
            System.out.println(pjp.getSignature().getName() + "环绕通知异常,异常{"+e+"},参数{"+ Arrays.asList(parms) +"}");
//            throw new RuntimeException(e);
        }
        return ret;
    }

    /**
     * div环绕通知前,参数{[1, 2]}
     * div开始,参数{[1, 2]}
     * div环绕通知后,返回值{0},参数{[1, 2]}
     * div结束,参数{[1, 2]}
     * div返回0,参数{[1, 2]}
     */

}

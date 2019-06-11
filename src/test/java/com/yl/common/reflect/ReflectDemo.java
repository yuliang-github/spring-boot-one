package com.yl.common.reflect;

import com.yl.common.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Alex
 * @since 2018/10/24 14:54
 */
public class ReflectDemo {

    public static void main(String[] args) {

        Class<UserMapperEx> mapperInterface = UserMapperEx.class;

        Method[] methods = mapperInterface.getMethods();
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        for (Method method : methods) {
            if(mapperInterface.equals(method.getDeclaringClass())){
                System.err.println("自身方法:" + method.getName());
            }
            Class<?>[] superInterfaces = mapperInterface.getInterfaces();
            for (Class<?> superInterface : superInterfaces) {
                if(method.getDeclaringClass().isAssignableFrom(superInterface)){
                    System.err.println(method.getName() + ":" + superInterface);
                }
            }
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.err.println(parameter.getName());
            }
            String[] parameterNames = discoverer.getParameterNames(method);
            System.err.println(Arrays.toString(parameterNames));
        }

        System.err.println("------------------------------------");
        Method[] declaredMethods = mapperInterface.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.err.println(declaredMethod);
        }

        System.err.println("------------------------------------");
        Method[] methodArr = mapperInterface.getMethods();
        for (Method method : methodArr) {
            System.err.println(method.getName());
        }

    }

    @Test
    public void demo_1(){
        Arrays.asList( UserClassEx.class.getMethods()).forEach(e -> {
            System.err.println(e);
        });

        System.err.println("-------------------");

        Arrays.asList(UserClassEx.class.getDeclaredMethods()).forEach(e -> {
            System.err.println(e);
        });
    }

    @Test
    public void demo_2(){
        BigDecimal money = new BigDecimal(1000);

        BigDecimal[] rets = money.divideAndRemainder(BigDecimal.valueOf(50));

        System.err.println(Arrays.asList(rets));
    }

    @Test
    public void demo_3(){

        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        Method[] methods = UserServiceImpl.class.getDeclaredMethods();
        for (Method method : methods) {
            String[] parameterNames = discoverer.getParameterNames(method);
            System.err.println(Arrays.toString(parameterNames));
        }

    }

}

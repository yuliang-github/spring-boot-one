package com.yl.common.reflect;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Alex
 * @since 2018/10/24 14:54
 */
public class ReflectDemo {

    public static void main(String[] args) {

        Class<UserMapperEx> mapperInterface = UserMapperEx.class;

        Method[] methods = mapperInterface.getMethods();

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

        System.err.println(Arrays.asList(UserClassEx.class.getDeclaredMethods()));
    }

}

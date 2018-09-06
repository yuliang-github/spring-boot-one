package com.yl.common.demo;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Alex
 * @since 2018/9/6 10:03
 */
public class Person {

//    @Value("#{1+2}")//SPEL赋值
    @Value("${person.id}")
    private int id;
//    @Value("miss")
    @Value("${person.name}")
    private String name;

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

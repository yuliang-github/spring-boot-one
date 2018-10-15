package com.yl.common.demo;

import java.io.Serializable;

/**
 * @author Alex
 * @since 2018/8/29 14:26
 */
public class User implements Serializable {

    private int id;

    private String name;

    public User(int id,String name){
        System.err.println("创建user");
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}

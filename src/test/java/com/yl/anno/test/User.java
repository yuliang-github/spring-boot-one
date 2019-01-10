package com.yl.anno.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @since 2019/1/4 10:44
 */
public class User {

    private String name;

    private List<Car> cars;

    public static class Car {

        private int no;

        private String name;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("王伟");

        List<Car> cars = new ArrayList<>();
        Car c1 = new Car();
        c1.setName("BMW");c1.setNo(1);
        Car c2 = new Car();
        c2.setName("BENZ");c1.setNo(2);
        cars.add(c1);cars.add(c2);

        user.setCars(cars);

        String json = JSON.toJSONString(user);

        System.err.println(json);

        User user_copy = JSON.parseObject(json, new TypeReference<User>() {
        });

        System.err.println(user_copy);
    }
}

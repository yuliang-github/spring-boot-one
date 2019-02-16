package com.yl.exception.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Alex
 * @since 2019/2/14 17:52
 */
public class ExceptionDemo {

    public static void main(String[] args) {

        try {
            demo();
        }catch (Exception e){
            System.err.println(e);
        }

    }


    public static void demo() throws DaoException{
        try {
            int i = 1/0;
        }finally {
            //throw new DaoException();
            //throw new RuntimeException(e);
            System.err.println("进入了finally");
        }
    }

    @Test
    public void demo_1(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(4);
        list.add(2);

        System.err.println(list);

        Collections.sort(list, (o1, o2) -> {
            return (o1-o2)>0?1:-1;
        });

        System.err.println(list);

        System.err.println(true == true);

    }

}

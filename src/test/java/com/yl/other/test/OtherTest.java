package com.yl.other.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @since 2018/9/5 11:14
 */
public class OtherTest {

    public static void main(String[] args) {

        String des = DemoBeanEx.Type.get(1).getDes();

        System.err.println(des);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        System.err.println(list);

        DemoBeanEx.syse();

        System.err.println(list);

        list.forEach(integer -> {
            System.err.println("V:" + integer);
        });

    }

}

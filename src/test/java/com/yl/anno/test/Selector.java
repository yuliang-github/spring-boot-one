package com.yl.anno.test;

/**
 * @author Alex
 * @since 2019/1/3 16:58
 */
public interface Selector {

    public Object select(Object value);

    public static void main(String[] args) {

        new Selector(){
            @Override
            public Object select(Object value) {
                return null;
            }
        }.getClass();

    }

}

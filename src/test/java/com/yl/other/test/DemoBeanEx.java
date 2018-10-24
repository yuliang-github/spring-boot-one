package com.yl.other.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 * @since 2018/9/5 11:14
 */
public class DemoBeanEx {

    public static enum Type {
        MAN(1,"男"),WOMAN(2,"女"),UNKNOW(3,"未知");

        private static Map<Integer,Type> cache = null;

        static {
            cache = new HashMap<>();
            Type[] types = Type.values();
            for (Type type : types) {
                cache.put(type.code, type);
            }
        }

        private int code;
        private String des;
        Type(int code,String des){
            this.code = code;
            this.des = des;
        }

        public String getDes(){
            return this.des;
        }

        public static Type get(int code){
            return cache.get(code);
        }
    }

    public static void syse(){
        System.err.println("调试技巧");
    }

}

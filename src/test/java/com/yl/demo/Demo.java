package com.yl.demo;

import org.junit.Test;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.util.ReflectionUtils;

import java.math.BigDecimal;

/**
 * @author Alex
 * @since 2019/1/17 16:51
 */
public class Demo {

    public static void main(String[] args) {
        String s = "1";
        for (byte tbyte:s.getBytes()){
            /**
             * 0xFF:0x表示16进制,0xFF二进制为1111 1111
             * 作用:高24位置零,低八位保持不变(因为负数转成int前24位会补1)
             * 0x100:二进制为0001 0000 0000即再加上一位
             */
            int i = (tbyte & 0xFF) + 0x100;
            String bs = Integer.toBinaryString(i);
            String tString = bs.substring(1);
            System.err.println(tString);
        }
    }

    @Test
    public void demo_1(){
        BigDecimal b = new BigDecimal(9);

        BigDecimal a = new BigDecimal(10);

        BigDecimal[] rets = b.divideAndRemainder(a);

        for (int i = 0; i < rets.length; i++) {
            System.err.println(i + ":" + rets[i]);
        }

    }

}

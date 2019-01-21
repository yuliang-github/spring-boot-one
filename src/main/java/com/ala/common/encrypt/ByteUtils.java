package com.ala.common.encrypt;

/**
 * @author Alex
 * @since 2019/1/21 14:37
 */
public class ByteUtils {

    /**
     * 获取字符串的二进制字符串
     * @param s
     * @return
     */
    public static String main(String s) {
        StringBuilder sb = new StringBuilder();
        for (byte tbyte:s.getBytes()){
            /**
             * 0xFF:0x表示16进制,0xFF二进制为1111 1111
             * 作用:高24位置零,低八位保持不变(因为负数转成int前24位会补1)
             * 0x100:二进制为0001 0000 0000即再加上一位
             */
            int i = (tbyte & 0xFF) + 0x100;
            String bs = Integer.toBinaryString(i);
            String tString = bs.substring(1);
            sb.append(tString);
        }
        return sb.toString();
    }

}

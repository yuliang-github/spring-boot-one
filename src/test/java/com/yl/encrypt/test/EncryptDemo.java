package com.yl.encrypt.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alex
 * @since 2019/1/10 10:18
 */
public class EncryptDemo {

    public static byte[] hex2byte(byte[] b) {
        if ((b.length & 1) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static byte[] hex2byte(String hexStr) {
        if (hexStr.length() % 2 != 0){
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[hexStr.length() / 2];
        for (int n = 0; n < hexStr.length(); n += 2) {
            String item = hexStr.substring(n, n + 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    @Test
    public void demo_1(){
        String hexStr = "e7";

        hex2byte(hexStr.getBytes());

        int i = Integer.parseInt("e7", 16);
        System.err.println(i);
    }

    @Test
    public void demo_2(){
        String key = "nbDLUw1eqV53HPwT";
        String hexString = AESUtils.encryptToHexString(""
            , key);
        System.err.println(hexString);

        System.err.println(AESUtils.decryptToString(hexString,key));

    }

    @Test
    public void demo_3()throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("AES");

        generator.init(128);

        SecretKey secretKey = generator.generateKey();

        byte[] encoded = secretKey.getEncoded();

        System.err.println(Base64.encodeBase64String(encoded).substring(0, 16));

    }

    @Test
    public void demo_4() throws Exception{
        String s = "abc";

        String hexStr = Hex.encodeHexString(s.getBytes());

        System.err.println(hexStr);

        byte[] bytes_1 = Hex.decodeHex(hexStr);

        byte[] bytes_2 = hex2byte(hexStr.getBytes());

        byte[] bytes_3 = hex2byte(hexStr);

    }


    @Test
    public void demo_5(){

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.err.println(df.format(new Date(1547693229360L)));
    }


}

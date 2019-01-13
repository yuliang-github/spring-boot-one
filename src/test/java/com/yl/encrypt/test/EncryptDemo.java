package com.yl.encrypt.test;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

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
        String key = "1drsmP6ndqpARrLa";
        String hexString = AESUtils.encryptToHexString("主要成就：创造了古代浪漫主义文学高峰、" +
            "歌行体和七绝达到后人难及的高度。吴江诗词网提供“李白”最诗歌详实的注译和最权威的点评" +
            ",愿为古诗文爱好者提供详实的参考。独坐敬亭山 众鸟高飞尽,孤云独去闲。相看两不厌,只有敬亭山。 " +
            "夜思 床前明月光, 疑是地上霜。 举头望明月, 低头思故乡。《江上吟 》是李白的一首诗作，" +
            "以其中一句“兴酣落笔摇五岳，诗成笑傲凌沧洲”广为流传，" +
            "更是被认为是李白最狂的诗句之一。不过若是站在李白于诗歌的成就上，" +
            "个人以为这一句可以称得上是最狂，并且是那种完全有实力的狂，令很多人不得不服的狂。", key);
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





}

package com.yl.encrypt.test;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Alex
 * @since 2019/1/10 10:31
 */
public class AESUtils {

    private static String AES = "AES";
    public static String encryptToHexString(String src,String key){
        try {
            Cipher cipher = Cipher.getInstance(AES);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(src.getBytes());
            return Hex.encodeHexString(bytes).toUpperCase();
        }catch (Exception e){

        }
        return null;
    }

    public static String decryptToString(String hexStr,String key){
        try {
            Cipher cipher = Cipher.getInstance(AES);
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] bytes1 = hexToByte(hexStr.getBytes());
            byte[] bytes = cipher.doFinal(bytes1);
            return new String(bytes);
        }catch (Exception e){
            System.err.println(e);
        }
        return null;
    }


    private static byte[] hexToByte(byte[] hexBytes){
        if(hexBytes.length % 2 != 0){
            throw new RuntimeException("长度不是偶数");
        }
        byte[] bytes = new byte[hexBytes.length / 2];
        for(int i = 0;i < hexBytes.length;i+=2){
            String tmp = new String(hexBytes, i, 2);
            bytes[i/2] = (byte)Integer.parseInt(tmp, 16);
        }
        return bytes;
    }




}

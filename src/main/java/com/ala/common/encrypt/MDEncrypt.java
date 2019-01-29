package com.ala.common.encrypt;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;

/**
 * @author Alex
 * @since 2019/1/9 14:36
 */
public class MDEncrypt {

    /**
     * 消息摘要算法-1
     *    MD(128位的摘要信息)
     *    主要有：MD2,MD4,MD5
     *    特点:
     *      1.产生128位的摘要信息
     *      2.不可逆
     *    应用:
     *      1.验证数据的完整性,防篡改。
     *      2.传输加密数据。如登录传输密码,使用MD5加密密码,服务端将数据库密码使用MD5加密后与传输的加密密码对比。
     *
     */

    public static void main(String[] args) throws Exception{
        String src = "password";
        jdkMd5(src);
        jdkMd2(src);
        bcMd2(src);
        ccMd2(src);
    }

    /**
     * jdk自带的MD5加密工具类
     * @param src
     * @throws Exception
     */
    public static void jdkMd5(String src) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("MD5");

        //digest.update(src.getBytes("UTF-8"));

        byte[] encrptBytes = digest.digest(src.getBytes("UTF-8"));

        System.err.println("jdk md5加密:" + byteToHex(encrptBytes));
    }
    /**
     * jdk自带的MD2加密工具类
     * @param src
     * @throws Exception
     */
    public static void jdkMd2(String src) throws Exception{
        MessageDigest digest = MessageDigest.getInstance("MD2");

        //digest.update(src.getBytes("UTF-8"));

        byte[] encrptBytes = digest.digest(src.getBytes("UTF-8"));

        System.err.println("jdk md2加密:" + byteToHex(encrptBytes));
    }

    public static String byteToHex(byte[] bytes){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Bouncy Castle的MD加密
     * @param src
     * @throws Exception
     */
    public static void bcMd2(String src) throws Exception{
        // 其余加密算法以此类推
        //Digest digest = new MD4Digest();
        Digest digest = new MD2Digest();
        byte[] srcBytes = src.getBytes("UTF-8");
        digest.update(srcBytes,0,srcBytes.length);

        byte[] encryptBytes = new byte[digest.getDigestSize()];
        digest.doFinal(encryptBytes, 0);

        System.err.println("bc md2加密:" + Hex.toHexString(encryptBytes));
    }

    /**
     * Apache commons codec的MD加密
     * 底层还是调用了jdk的加密工具,只是简化了jdk的操作
     * @param src
     * @throws Exception
     */
    public static void ccMd2(String src) throws Exception{
        System.err.println("cc MD2加密:" + DigestUtils.md2Hex(src));
        System.err.println("cc MD5加密:" + DigestUtils.md5Hex(src));
    }

}

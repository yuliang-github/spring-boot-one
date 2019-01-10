package com.ala.common.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Alex
 * @since 2019/1/9 16:22
 */
public class HMACEncrypt {

    /**
     * 消息摘要算法-3
     *      HMAC算法(消息认证码算法、含有密钥的hash散列算法)
     *      简介:HMAC算法除了需要信息摘要算法外，还需要一个密钥。HMAC的密钥可以是任何长度，
     *          如果密钥的长度超过了摘要算法信息分组的长度，则首先使用摘要算法计算密钥的摘
     *          要作为新的密钥。一般不建议使用太短的密钥，因为密钥的长度与安全强度是相关的。
     *          通常选取密钥长度不小于所选用摘要算法输出的信息摘要的长度。
     *
     * 优点和应用场景:
     *      1.在网络传输中,如果使用MD和SHA摘要算法,可能被黑客篡改原始数据和摘要数据，
     *        导致服务器验证通过而达到攻击目的,HMAC算法通过密钥和数据共同生成消息摘要
     *        在不知道秘钥的情况下,伪造消息摘要的难度将会加大。
     *
     * 算法	      摘要长度	备注
     * HmacMD5	    128	  BouncyCastle实现
     * HmacSHA1	    160	  BouncyCastle实现
     * HmacSHA256	256	  BouncyCastle实现
     * HmacSHA384	384	  BouncyCastle实现
     * HmacSHA512	512	  JDK实现
     * HmacMD2	    128	  BouncyCastle实现
     * HmacMD4	    128	  BouncyCastle实现
     * HmacSHA224   224	  BouncyCastle实现
     *
     */

    public static void main(String[] args) throws Exception{
        String src = "password";
        jdkHmacMd5(src);
        bcHmacMd5(src);

    }

    /**
     * JDK实现的Hmacmd5算法
     * @param src
     */
    public static void jdkHmacMd5(String src) throws Exception{
       // KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");

        // SecretKey secretKey = keyGenerator.generateKey();

       // byte[] keyEncoded = secretKey.getEncoded();//获得密钥

        //还原密钥
        byte[] key = Hex.decodeHex(new char[]{'1', '2', '3', '4', 'a', 'b', 'c', 'd'});
        SecretKey restoreKey = new SecretKeySpec(key,"HmacMD5");
        // 实例化mac
        Mac mac = Mac.getInstance(restoreKey.getAlgorithm());
        // 初始化Mac
        mac.init(restoreKey);
        byte[] hmacMd5Bytes = mac.doFinal(src.getBytes());

        System.err.println("jdk hmacmd5加密:" + Hex.encodeHexString(hmacMd5Bytes));
    }


    public static void bcHmacMd5(String src){
        HMac hmac = new HMac(new MD5Digest());

        hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("1234abcd")));

        hmac.update(src.getBytes(), 0,src.getBytes().length);

        byte[] bytes = new byte[hmac.getMacSize()];

        hmac.doFinal(bytes, 0);

        System.err.println("bc Hmac加密:" + org.bouncycastle.util.encoders.Hex.toHexString(bytes));

    }

}

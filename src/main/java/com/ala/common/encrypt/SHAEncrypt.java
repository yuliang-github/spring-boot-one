package com.ala.common.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 * @since 2019/1/9 15:15
 */
public class SHAEncrypt {

    /**
     * 消息摘要算法-2
     *      SHA(HASH散列算法)
     *      系列:
     *          1.SHA-1:生成160bit的摘要信息
     *          2.SHA-2
     *                  SHA-256:生成长度256bit的信息摘要。
     *                  SHA-224:SHA-256的“阉割版”，可以生成长度224bit的信息摘要。
     *                  SHA-512:可以生成长度512bit的信息摘要。
     *                  SHA-384:SHA-512的“阉割版”，可以生成长度384bit的信息摘要。
     *       特点:
     *          1.摘要信息越长,发生碰撞的几率就越低，破解的难度就越大。但同时，耗费的性能和占用的空间也就越高
     *
     *       拓展知识点:
     *          1.合成摘要:取MD摘要的前16和SHA-256摘要的后32位，拼成一个长度48位的合成摘要
     */

    public static void main(String[] args) throws Exception{
        String src = "abcd";
        jdkSHA(src);
        bcSHA(src);
        ccSHA(src);
        fixMDSHA(src);
    }

    /**
     * jdk实现的SHA加密encryptBytes
     * @param src
     * @throws Exception
     */
    public static void jdkSHA(String src) throws Exception{
        // 其它类似
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] encryptBytes = digest.digest(src.getBytes("UTF-8"));

        System.err.println("jdk sha加密:" + Hex.encodeHexString(encryptBytes));

    }

    /**
     * jdk实现的SHA加密
     * @param src
     * @throws Exception
     */
    public static void bcSHA(String src) throws Exception{
        // 其它类似
        Digest digest = new SHA256Digest();
        byte[] srcBytes = src.getBytes("UTF-8");
        digest.update(srcBytes,0,srcBytes.length);

        byte[] encryptBytes = new byte[digest.getDigestSize()];
        digest.doFinal(encryptBytes, 0);

        System.err.println("bc sha加密:" + org.bouncycastle.util.encoders.Hex.toHexString(encryptBytes));

    }


    /**
     * jdk实现的SHA加密
     * @param src
     * @throws Exception
     */
    public static void ccSHA(String src) throws Exception{
        // 其它类似
        String encryptStr = DigestUtils.sha256Hex(src);
        System.err.println("cc sha加密:" + encryptStr);
    }

    /**
     * 合成摘要的实现
     */
    public static void fixMDSHA(String src){
        String md5 = DigestUtils.md5Hex(src).substring(0, 16);
        String sha256 = DigestUtils.sha256Hex(src).substring(32);
        System.err.println("MD5+SHA256合成摘要:" + md5 + sha256);
    }
}

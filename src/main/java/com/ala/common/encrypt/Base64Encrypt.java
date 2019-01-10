package com.ala.common.encrypt;

import java.util.Base64;

/**
 * @author Alex
 * @since 2019/1/9 11:16
 */
public class Base64Encrypt {

    /**
     *  Base64加密
     *   1.严格来说,Base64只能算一种编码方式,而不是加密方式
     *   2.原理:
     *      1.用ASCII的64个可打印字符(一共95个可打印字符)来表示二进制数据
     *          1.1.在ASCII码中规定,0-31、127这33个字符属于控制字符,32-126这95个字符属于可打印字符
     *      2.Base64的索引为0-63,至多使用6个bit(000000~111111 = 0~63)
     *      3.使用6个bit位存储8个bit,故将要存储的字节的二进制数据按6个bit切分,高位补零,转成索引对应的字符
     *          3.1.3个ASCII字符可转成4个BASE64字符,当要转换的字符数不是3的倍数时,采用补0的方式凑足3的倍数
     *          3.2.补零的剩下的8为bit使用字符'='代替
     *          例:
     *                    S
     *     十进制         83
     *     二进制       01010011
     *  每6bit为一组    010100    110000    000000    000000
     *  高位补0       00010100  00110000  00000000  00000000
     *  对应Base64索引  20         48
     *  对应Base64字符  U          W       =          =
     */


    public static void main(String[] args) throws Exception{
        String src = "S";

        jdkBase64(src);

        bcBase64(src);

        ccBase64(src);
    }

    /**
     * jdk自带Base64编码工具类
     * @param src
     * @throws Exception
     */
    public static void jdkBase64(String src) throws Exception{
        String base64Encode = Base64.getEncoder().encodeToString(src.getBytes("UTF-8"));

        System.err.println("jdk bse64编码:" + base64Encode);

        byte[] base64Decode = Base64.getDecoder().decode(base64Encode);

        System.err.println("jdk base64解码:" + new String(base64Decode, "UTF-8"));
    }

    /**
     * Bouncy Castle的Base64编码
     * @param src
     * @throws Exception
     */
    public static void bcBase64(String src) throws Exception{
        String base64Encode = org.bouncycastle.util.encoders.Base64.toBase64String(src.getBytes("UTF-8"));

        System.err.println("bc bse64编码:" + base64Encode);

        byte[] base64Decode = org.bouncycastle.util.encoders.Base64.decode(base64Encode);

        System.err.println("bc base64解码:" + new String(base64Decode, "UTF-8"));
    }

    /**
     * Apache commons codec的Base64编码
     * @param src
     * @throws Exception
     */
    public static void ccBase64(String src) throws Exception{
        String base64Encode = org.apache.commons.codec.binary.Base64.encodeBase64String(src.getBytes("UTF-8"));

        System.err.println("cc bse64编码:" + base64Encode);

        byte[] base64Decode = org.apache.commons.codec.binary.Base64.decodeBase64(base64Encode);

        System.err.println("cc base64解码:" + new String(base64Decode, "UTF-8"));
    }
}

package com.hnnd.util;

import org.apache.commons.codec.digest.DigestUtils;

/**md5加密工具
 * Created by 85073 on 2018/3/29.
 */
public class MD5Utils {

    /**
     * 固定密钥 和前端的相同，用于第一次md5
     */
    private static final String MD5_SALT="1a2b3c4d";

    /**
     * md5加密
     * @param src 源数据
     * @return md5字符串
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    /**
     * 第一次md5
     * @param src
     * @return
     */
    public static String firstMd5(String src) {
        String target =""+ MD5_SALT.charAt(0)+MD5_SALT.charAt(2)+src+
                        MD5_SALT.charAt(MD5_SALT.length()-2)+
                        MD5_SALT.charAt(MD5_SALT.length()-1);
        return md5(target);
    }

    /**
     *
     * @param firstMd5  第一次加密的md5字符串
     * @param randomSalt 随机密钥需要存到数据库
     * @return 第二次md5值
     */
    public static String secondMd5(String firstMd5,String randomSalt) {
        String target = ""+randomSalt.charAt(0)+randomSalt.charAt(2)+firstMd5+
                randomSalt.charAt(randomSalt.length()-2)+
                randomSalt.charAt(randomSalt.length()-1);
        return md5(target);
    }

    /**
     * 从源字符串直接2次md5
     * @param src 源字符串
     * @param randomSalt 随机密钥 需要入库
     * @return 二次md5的值
     */
    public static String lastMd5(String src,String randomSalt){
        return secondMd5(firstMd5(src),randomSalt);
    }

    public static void main(String[] args) {
        String password = "123456";
        System.out.println("原密码:"+password);
        System.out.println("第一次md5:"+firstMd5(password));
        System.out.println("第二次md5:"+lastMd5(password,"333aaabbb"));
    }
}

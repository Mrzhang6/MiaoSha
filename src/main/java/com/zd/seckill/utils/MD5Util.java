package com.zd.seckill.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zd
 * @date 2022/4/27 14:41
 * 描述：MD5工具类
 */
public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private  static  final String salt = "1a2b3c4d";

    public static String inputPassToFromPass(String inputPass){
        String str = ""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String fromPassToDBPass(String fromPass,String salt){
        String str  = ""+salt.charAt(0)+salt.charAt(2)+fromPass+salt.charAt(5)+salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDBPass(String inputPass,String salt){
        String fromPass = inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, salt);
        return dbPass;
    }

    public static void main(String[] args) {
        String s = "123456";
        System.out.println(inputPassToFromPass(s));
        System.out.println(inputPassToDBPass(s,salt));
    }
}

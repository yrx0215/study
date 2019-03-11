package com.jnshu.dreamteam.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {

    /**
     * md5加盐加密
     * @param str
     * @param salt
     * @return 散列后的字符串
     */

    public static<T> String stringMD5 (String str,T salt){
        try{
            // 拿到一个MD5转换器
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String input = Md5Utils.Splice(str,salt);
            // 输入的字符串转换成字节数组
            byte[] inputByte = input.getBytes();
            //把转换后的数组添加到messageDigest中
            messageDigest.update(inputByte);
            byte[] resultByteArray = messageDigest.digest();

            return byte2Hex(resultByteArray);
        }catch (NoSuchAlgorithmException e){
            return "";
        }
    }

    private static String byte2Hex(byte[] resultByteArray){
        StringBuilder stringBuilder = new StringBuilder();
        for(byte bytes:resultByteArray){
            int temp =0xff & bytes;
            String hexString = Integer.toHexString(temp);
            if(hexString.length()==1){
                stringBuilder.append("0").append(hexString);
            }else {
                stringBuilder.append(hexString);
            }
        }
        System.out.println(stringBuilder.length());
        return stringBuilder.toString();
    }


    /**
     * 将盐和字符串拼接起来
     * @param str
     * @param salt
     * @return
     */
    private static <T> String Splice(String str, T salt){
        StringBuilder sb = new StringBuilder();
        sb.append(str).append(salt);
        return ""+sb;
    }
}

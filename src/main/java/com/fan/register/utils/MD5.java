package com.fan.register.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    
    //密码加密
    public static String toMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] strByteArray = str.getBytes("utf-8");
        byte[] mdByteArray = md.digest(strByteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < mdByteArray.length; i++) {
            int val = ((int) mdByteArray[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}

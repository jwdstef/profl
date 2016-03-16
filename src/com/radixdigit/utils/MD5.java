package com.radixdigit.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * MD5Utils
 */
public class MD5 {
    private MessageDigest md;
    private static MD5 md5;

    private MD5() {
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    // create a MD5 instance
    public static MD5 getInstance() {
        if (null != md5)
            return md5;
        else {
            makeInstance();
            return md5;
        }
    }

    // only one thread using MD5 encrpytion in the same time
    private static synchronized void makeInstance() {
        if (null == md5)
            md5 = new MD5();
    }

    public String createMD5(String passwd) {
        md.update(passwd.getBytes());
        byte[] b = md.digest();
        return byteToHexString(b);
    }

    private String byteToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        String temp = "";
        for (int i = 0; i < b.length; i++) {
            temp = Integer.toHexString(b[i] & 0Xff);
            if (temp.length() == 1)
                temp = "0" + temp;
            sb.append(temp);
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        MD5 md5 = new MD5();
        System.out.println(md5.createMD5("admin123"));
    }
}

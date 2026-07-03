package com.javaee.clientinfomanagesystem.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String encrypt(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(str.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest){
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}

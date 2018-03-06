package com.lhzcpan.util;

import java.security.MessageDigest;

/**
 * AES对称加密和解密
 *
 * @author master
 * @date 2018/1/12
 */

public class SymmetricEncoder {

    /**
     * SHA-256 散列算法加密数据(不可逆)
     *
     * @param content
     * @return
     */
    public static String SHA256Encode(String content) {
        String encodeStr = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(content.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {

        }

        return encodeStr;
    }

    private static String byte2Hex(byte[] digest) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp;
        for (int i = 0; i < digest.length; i++) {
            temp = Integer.toHexString(digest[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }


    /**
     * 加密
     *
     * @param encodeRules
     * @param content
     * @return
     */
    public static String AESEncode(String encodeRules, String content) {

        try {

        } catch (Exception e) {

        }

        return null;
    }


    /**
     * 解密
     *
     * @param encodeRules
     * @param content
     * @return
     */
    public static String AESDecode(String encodeRules, String content) {

        try {

        } catch (Exception e) {

        }

        return null;
    }


}

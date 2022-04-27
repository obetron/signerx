package com.gelecex.signerx.utils;

import com.gelecex.signerx.common.EnumHashAlgorithm;
import com.gelecex.signerx.common.exception.SignerxException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by obetron on 27.04.2022
 */
public class SignerxUtils {

    public static byte[] calculateHash(byte[] val, String digestAlg) throws SignerxException {
        if(digestAlg.equals("SHA256") || digestAlg.equals("SHA-256")) {
            return calculateHash(val, EnumHashAlgorithm.SHA256);
        } else if(digestAlg.equals("SHA512") || digestAlg.equals("SHA-512")) {
            return calculateHash(val, EnumHashAlgorithm.SHA512);
        } else {
            throw new SignerxException("Desteklenmeyen Ozet Algoritması Hatası!!!");
        }
    }

    public static byte[] calculateHash(byte[] val, EnumHashAlgorithm gelecexDigestAlg) throws SignerxException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(gelecexDigestAlg.toString());
            return messageDigest.digest(val);
        } catch (NoSuchAlgorithmException e) {
            throw new SignerxException("Hatali mesaj ozeti!", e);
        }
    }

    public static String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }
}

package com.gelecex.smartcard.utils;

import com.gelecex.smartcard.exception.GelecexSignerException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by obetron on 18.11.2018
 */
public class GelecexUtils {

    public static byte[] calculateHash(byte[] val, String digestAlg) throws GelecexSignerException {
        if(digestAlg.equals("SHA256") || digestAlg.equals("SHA-256")) {
            return calculateHash(val, GelecexDigestAlg.SHA256);
        } else if(digestAlg.equals("SHA512") || digestAlg.equals("SHA-512")) {
            return calculateHash(val, GelecexDigestAlg.SHA512);
        } else {
            throw new GelecexSignerException("Desteklenmeyen Ozet Algoritması Hatası!!!");
        }
    }

    public static byte[] calculateHash(byte[] val, GelecexDigestAlg gelecexDigestAlg) throws GelecexSignerException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(gelecexDigestAlg.toString());
            return messageDigest.digest(val);
        } catch (NoSuchAlgorithmException e) {
            throw new GelecexSignerException("Hatali mesaj ozeti!", e);
        }
    }

}

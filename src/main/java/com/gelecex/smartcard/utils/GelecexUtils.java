package com.gelecex.smartcard.utils;

import com.gelecex.smartcard.enums.GelecexDigestAlg;
import com.gelecex.smartcard.exception.GelecexSignerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

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

    public static String readGelecexConfigFile(String key) {
        try {
            Properties properties = new Properties();
            properties.load(GelecexUtils.class.getResourceAsStream("/gelecex_config.properties"));
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

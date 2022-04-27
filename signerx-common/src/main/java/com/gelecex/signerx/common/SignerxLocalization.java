package com.gelecex.signerx.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by obetron on 22.12.2018
 */
public class SignerxLocalization {

    private static ResourceBundle resourceBundle;
    private static String message;

    public static String getErrorMessage(String str) {
        return getMessage(str, "errors");
    }

    public static String getWarningMessage(String str) {
        return getMessage(str, "warning");
    }

    public static String getInfoMessage(String str) {
        return getMessage(str, "info");
    }

    private static String getMessage(String msg, String type) {
        resourceBundle = ResourceBundle.getBundle(type,
                new Locale(readGelecexConfigFile("gelecex.lang")));
        message = resourceBundle.getString(msg);
        return message;
    }

    private static String readGelecexConfigFile(String key) {
        try {
            Properties properties = new Properties();
            properties.load(SignerxLocalization.class.getResourceAsStream("/gelecex_config.properties"));
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

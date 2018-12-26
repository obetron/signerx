package com.gelecex.smartcard.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by obetron on 22.12.2018
 */
public class GelecexI18n {

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
                new Locale(GelecexUtils.readGelecexConfigFile("gelecex.lang")));
        message = resourceBundle.getString(msg);
        return message;
    }
}

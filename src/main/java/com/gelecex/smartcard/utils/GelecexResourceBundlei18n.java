package com.gelecex.smartcard.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by obetron on 22.12.2018
 */
public class GelecexResourceBundlei18n {

    private static ResourceBundle resourceBundle;
    private static String message;

    public static String getMessage(String str) {
        resourceBundle = ResourceBundle.getBundle("errors", new Locale(GelecexUtils.readGelecexConfigFile("gelecex.lang")));
        message = resourceBundle.getString(str);
        return message;
    }
}

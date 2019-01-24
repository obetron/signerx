package com.gelecex.smartcard.parser;

/**
 * Created by obetron on 12.01.2019
 */
public class XMLAttributes {

    private String name;
    private String value;

    public XMLAttributes() {
        //do nothing
    }

    public XMLAttributes(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

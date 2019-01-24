package com.gelecex.smartcard.model;

import java.util.List;

/**
 * Created by obetron on 5.01.2019
 */
public class SmartcardType {
    
    private String name;
    private SmartcardLibrary library;
    private List<String> atrValues;

    public SmartcardType() {
    }

    public SmartcardType(String name, SmartcardLibrary library, List<String> atrValues) {
        this.name = name;
        this.library = library;
        this.atrValues = atrValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SmartcardLibrary getLibrary() {
        return library;
    }

    public void setLibrary(SmartcardLibrary library) {
        this.library = library;
    }

    public List<String> getAtrValues() {
        return atrValues;
    }

    public void setAtrValues(List<String> atrValues) {
        this.atrValues = atrValues;
    }
}

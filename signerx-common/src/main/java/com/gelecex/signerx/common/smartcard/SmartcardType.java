package com.gelecex.signerx.common.smartcard;

import java.util.List;

/**
 * Created by obetron on 26.04.2022
 */
public class SmartcardType {

    private String name;
    private List<SmartcardLibrary> libraryList;
    private List<SmartcardAtr> atrList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SmartcardLibrary> getLibraryList() {
        return libraryList;
    }

    public void setLibraryList(List<SmartcardLibrary> libraryList) {
        this.libraryList = libraryList;
    }

    public List<SmartcardAtr> getAtrList() {
        return atrList;
    }

    public void setAtrList(List<SmartcardAtr> atrList) {
        this.atrList = atrList;
    }
}

package com.gelecex.smartcard.model;

import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public class Smartcard {

    private String cardType;
    private List<String> atrValueList;
    private List<SmartcardLibrary> smartcardLibraryList;

    public Smartcard() {
    }

    public Smartcard(String cardType, List<String> atrValueList, List<SmartcardLibrary> smartcardLibraryList) {
        this.cardType = cardType;
        this.atrValueList = atrValueList;
        this.smartcardLibraryList = smartcardLibraryList;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public List<String> getAtrValueList() {
        return atrValueList;
    }

    public void setAtrValueList(List<String> atrValueList) {
        this.atrValueList = atrValueList;
    }

    public List<SmartcardLibrary> getSmartcardLibraryList() {
        return smartcardLibraryList;
    }

    public void setSmartcardLibraryList(List<SmartcardLibrary> smartcardLibraryList) {
        this.smartcardLibraryList = smartcardLibraryList;
    }
}
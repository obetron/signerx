package com.gelecex.smartcard.model;

import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public class Smartcard {

    private String cardType;
    private List<String> atrValues;
    private SmartcardLibrary smartcardLibrary;

    public Smartcard() {
    }

    public Smartcard(String cardType, List<String> atrValues, SmartcardLibrary smartcardLibrary) {
        this.cardType = cardType;
        this.atrValues = atrValues;
        this.smartcardLibrary = smartcardLibrary;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public List<String> getAtrValues() {
        return atrValues;
    }

    public void setAtrValues(List<String> atrValues) {
        this.atrValues = atrValues;
    }

    public SmartcardLibrary getSmartcardLibrary() {
        return smartcardLibrary;
    }

    public void setSmartcardLibrary(SmartcardLibrary smartcardLibrary) {
        this.smartcardLibrary = smartcardLibrary;
    }
}
package com.gelecex.smartcard.model;

/**
 * Created by obetron on 18.12.2018
 */
public class Smartcard {

    private String cardType;
    private String library;
    private String atr;

    public Smartcard() {
    }

    public Smartcard(String cardType, String library, String atr) {
        this.cardType = cardType;
        this.library = library;
        this.atr = atr;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getAtr() {
        return atr;
    }

    public void setAtr(String atr) {
        this.atr = atr;
    }
}

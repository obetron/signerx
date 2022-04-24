package com.gelecex.signerx.common;

import java.util.List;

/**
 * Created by obetron on 24.04.2022
 */
public class SmartcardDetail {

    private String cardType;
    private String library;
    private List<String> atrValues;

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

    public List<String> getAtrValues() {
        return atrValues;
    }

    public void setAtrValues(List<String> atrValues) {
        this.atrValues = atrValues;
    }
}

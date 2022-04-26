package com.gelecex.signerx.common.smartcard;

import java.util.List;

/**
 * Created by obetron on 26.04.2022
 */
public class SmartcardConfig {

    private List<SmartcardType> cardTypeList;

    public List<SmartcardType> getCardTypeList() {
        return cardTypeList;
    }

    public void setCardTypeList(List<SmartcardType> cardTypeList) {
        this.cardTypeList = cardTypeList;
    }
}

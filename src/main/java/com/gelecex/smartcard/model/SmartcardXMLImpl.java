package com.gelecex.smartcard.model;

import com.gelecex.smartcard.exception.SmartcardReaderException;
import com.gelecex.smartcard.utils.GelecexI18n;

import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class SmartcardXMLImpl implements SmartcardDao {

    private List<Smartcard> smartcards;

    @Override
    public void saveSmartcard(Smartcard smartcard) {

    }

    @Override
    public void updateSmartcard(Smartcard smartcard) {

    }

    @Override
    public void deleteSmartcard(Smartcard smartcard) {

    }

    @Override
    public Smartcard getSmartcard(String lib) {
        return null;
    }

    @Override
    public List<Smartcard> getSmartcardList() throws SmartcardReaderException {
        if(smartcards != null && !smartcards.isEmpty()) {
            return smartcards;
        } else {
            throw new SmartcardReaderException(GelecexI18n.getErrorMessage("smartcardReaderError"));
        }
    }

    private void readAllSmartcardsFromXMLFile() {

    }

}

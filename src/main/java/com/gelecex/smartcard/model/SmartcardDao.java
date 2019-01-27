package com.gelecex.smartcard.model;

import com.gelecex.smartcard.exception.SmartcardReaderException;
import com.gelecex.smartcard.exception.XMLParserException;

import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public interface SmartcardDao {

    void saveSmartcard(Smartcard smartcard);
    void updateSmartcard(Smartcard smartcard);
    void deleteSmartcard(Smartcard smartcard);
    Smartcard getSmartcard(String lib);
    List<Smartcard> getSmartcardList() throws SmartcardReaderException, XMLParserException;

}

package com.gelecex.signerx.service;

import com.gelecex.signerx.exception.SignerxSmartcardException;
import com.gelecex.signerx.exception.SignerxXMLParserException;
import com.gelecex.signerx.model.Smartcard;

import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public interface SmartcardDao {

    void saveSmartcard(Smartcard smartcard);
    void updateSmartcard(Smartcard smartcard);
    void deleteSmartcard(Smartcard smartcard);
    Smartcard getSmartcard(String atrValue) throws SignerxXMLParserException;
    List<Smartcard> getSmartcardList() throws SignerxSmartcardException, SignerxXMLParserException;

}

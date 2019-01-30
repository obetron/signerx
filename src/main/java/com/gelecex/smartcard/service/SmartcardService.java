package com.gelecex.smartcard.service;

import com.gelecex.smartcard.exception.XMLParserException;
import com.gelecex.smartcard.model.Smartcard;

import javax.smartcardio.CardException;
import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public interface SmartcardService {

    List<Smartcard> readSmartcardToken() throws CardException, XMLParserException;

}

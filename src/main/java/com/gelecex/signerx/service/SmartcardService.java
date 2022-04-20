package com.gelecex.signerx.service;

import com.gelecex.signerx.exception.SignerxXMLParserException;
import com.gelecex.signerx.model.Smartcard;

import javax.smartcardio.CardException;
import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public interface SmartcardService {

    List<Smartcard> readSmartcardToken() throws CardException, SignerxXMLParserException;

}

package com.gelecex.signerx.service;

import com.gelecex.signerx.model.Smartcard;
import org.xml.sax.SAXException;

import javax.smartcardio.CardException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by obetron on 18.12.2018
 */
public interface SmartcardService {

    List<Smartcard> readSmartcardToken() throws CardException, ParserConfigurationException, IOException, SAXException;

}

package com.gelecex.signerx.service;

import com.gelecex.signerx.model.Smartcard;
import com.gelecex.signerx.utils.SignerxUtils;
import org.xml.sax.SAXException;

import javax.smartcardio.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obetron on 30.01.2019
 */
public class SmartcardServiceImpl implements SmartcardService {

//    private Logger LOGGER = Logger.getLogger(SmartcardServiceImpl.class);

    @Override
    public List<Smartcard> readSmartcardToken() throws CardException, ParserConfigurationException, IOException, SAXException {
        TerminalFactory terminalFactory = TerminalFactory.getDefault();
        CardTerminals cardTerminals = terminalFactory.terminals();
        List<Smartcard> smartcardList = new ArrayList<>();
        for (CardTerminal cardTerminal : cardTerminals.list()) {
            try {
                Card card = cardTerminal.connect("*");
                if(card.getATR() != null) {
                    String atrValue = SignerxUtils.byteToHex(card.getATR().getBytes());
                    SmartcardDao smartcardDao = new SmartcardXMLImpl();
                    Smartcard smartcard = smartcardDao.getSmartcard(atrValue);
                    if (smartcard != null) {
                        smartcardList.add(smartcard);
                    }
                } else {
//                    LOGGER.error("Smartcard ATR value could not read! - " + cardTerminal.getName());
                }
            } catch(CardNotPresentException e) {
//                LOGGER.debug("No plugged card found for installed smartcard driver!");
                continue;
            }
        }
        return smartcardList;
    }
}

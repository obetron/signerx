package com.gelecex.smartcard.service;

import com.gelecex.smartcard.exception.XMLParserException;
import com.gelecex.smartcard.model.Smartcard;
import com.gelecex.smartcard.utils.GelecexUtils;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.TerminalFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obetron on 30.01.2019
 */
public class SmartcardServiceImpl implements SmartcardService {

    @Override
    public List<Smartcard> readSmartcardToken() throws CardException, XMLParserException {
        TerminalFactory terminalFactory = TerminalFactory.getDefault();
        CardTerminals cardTerminals = terminalFactory.terminals();
        List<Smartcard> smartcardList = new ArrayList<>();
        for (CardTerminal cardTerminal : cardTerminals.list()) {
            Card card = cardTerminal.connect("*");
            String atrValue = GelecexUtils.byteToHex(card.getATR().getBytes());
            SmartcardDao smartcardDao = new SmartcardXMLImpl();
            Smartcard smartcard = smartcardDao.getSmartcard(atrValue);
            if(smartcard != null) {
                smartcardList.add(smartcard);
            }
        }
        return smartcardList;
    }
}

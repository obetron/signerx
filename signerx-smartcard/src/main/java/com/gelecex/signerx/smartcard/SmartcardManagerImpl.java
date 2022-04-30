package com.gelecex.signerx.smartcard;

/**
 * Created by obetron on 27.04.2022
 */

import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.utils.SignerxUtils;

import javax.smartcardio.*;
import java.util.ArrayList;
import java.util.List;

public class SmartcardManagerImpl implements SmartcardManager {

    @Override
    public void detectSmartcards() throws SignerxException {
        List<String> smartcardAtrList = getAtrFromSmartcards();
    }

    private List<String> getAtrFromSmartcards() throws SignerxException {
        try {
            List<String> smartcardAtrList = new ArrayList<>();
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals terminals = terminalFactory.terminals();
            List<CardTerminal> cardTerminalList = terminals.list();
            for (CardTerminal cardTerminal : cardTerminalList) {
                Card card = cardTerminal.connect("T0");
                ATR atr = card.getATR();
                byte[] atrBytes = atr.getBytes();
                smartcardAtrList.add(SignerxUtils.byteToHex(atrBytes));
            }
            return smartcardAtrList;
        } catch (CardException e) {
            throw new SignerxException("Terminal listesi alinirken hata olustu!", e);
        }
    }

    private void detectSmartcardLib(String atrValue) throws SignerxException {

    }
}

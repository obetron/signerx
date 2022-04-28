package com.gelecex.signerx.smartcard;

/**
 * Created by obetron on 27.04.2022
 */

import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.utils.SignerxUtils;

import javax.smartcardio.*;
import java.util.List;

public class SmartcardManagerImpl implements SmartcardManager {

    @Override
    public String getAtrFromSmartcard() throws SignerxException {
        try {
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals terminals = terminalFactory.terminals();
            List<CardTerminal> cardTerminalList = terminals.list();
            Card card = cardTerminalList.get(0).connect("T0");
            ATR atr = card.getATR();
            byte[] atrBytes = atr.getBytes();
            return SignerxUtils.byteToHex(atrBytes);
        } catch (CardException e) {
            throw new SignerxException("Terminal listesi alinirken hata olustu!", e);
        }
    }

    @Override
    public void detectSmartcardLib(String atrValue) throws SignerxException {

    }
}

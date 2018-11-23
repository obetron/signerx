package com.gelecex.signer;

import com.gelecex.signer.exception.GelecexSignerException;
import com.gelecex.signer.utils.TubitakSettingsUploader;
import org.apache.log4j.Logger;
import tr.gov.tubitak.uekae.esya.api.common.util.bag.Pair;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by obetron on 21.11.2018
 */
public class GelecexSmartcardReader {

    private static final Logger LOGGER = Logger.getLogger(GelecexSmartcardReader.class);

    public List<GelecexTerminal> getTerminalList() throws GelecexSignerException {
        List<GelecexTerminal> gelecexTerminalList = new ArrayList<>();
        List<String> gelecexTerminals = getTerminals();
        for (String glcxTerminal : gelecexTerminals) {
            GelecexTerminal gelecexTerminal = new GelecexTerminal();
            gelecexTerminal.setTerminalName(glcxTerminal);
            HashMap<Long, CardType> gelecexSmartcardObjects = getSmartcardObjects(glcxTerminal);
        }
        return gelecexTerminalList;
    }

    private List<String> getTerminals() throws GelecexSignerException {
        TubitakSettingsUploader.licenseFileUploader();
        try {
            String[] terminals = SmartOp.getCardTerminals();
            LOGGER.debug(terminals.length + " adet takili terminal bulundu.");
            return Arrays.asList(terminals);
        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart hatası" ,e);
        }

    }

    private HashMap<Long, CardType> getSmartcardObjects(String terminal) throws GelecexSignerException {
        HashMap<Long, CardType> smartcardObjects = new HashMap<>();
        TubitakSettingsUploader.licenseFileUploader();
        try {
            Pair<Long, CardType> smartcard = SmartOp.getSlotAndCardType(terminal);
            if(smartcard != null) {
                smartcardObjects.put(smartcard.getObject1(), smartcard.getObject2());
                LOGGER.debug("Akıllı kart slot ve kart tipi değerleri alındı.");
            }

        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart nesneleri alınırken hata oluştu!", e);
        }
        return smartcardObjects;
    }
}

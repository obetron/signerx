package com.gelecex.signer;

import com.gelecex.signer.exception.GelecexSignerException;
import com.gelecex.signer.utils.TubitakSettingsUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tr.gov.tubitak.uekae.esya.api.common.util.bag.Pair;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by obetron on 21.11.2018
 */
public class GelecexSmartcardReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(GelecexSmartcardReader.class);
    private Long smartcardSlot;
    private CardType smarcardType;

    public List<String> getTerminalList() throws GelecexSignerException {

        TubitakSettingsUploader.licenseFileUploader();
        try {
            String[] terminals = SmartOp.getCardTerminals();
            LOGGER.debug(terminals.length + " adet takili terminal bulundu.");
            return Arrays.asList(terminals);
        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart hatası" ,e);
        }

    }

    public void getSmartcardObjects(String terminal) throws GelecexSignerException {
        TubitakSettingsUploader.licenseFileUploader();
        try {
            Pair<Long, CardType> smartcard = SmartOp.getSlotAndCardType(terminal);
            if(smartcard != null) {
                smartcardSlot = smartcard.getObject1();
                smarcardType = smartcard.getObject2();
                LOGGER.debug("Akıllı kart slot ve kart tipi değerleri alındı.");
            }

        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart nesneleri alınırken hata oluştu!", e);
        }
    }
}

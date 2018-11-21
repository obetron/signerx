package com.gelecex.signer;

import com.gelecex.signer.exception.GelecexSignerException;
import com.gelecex.signer.utils.TubitakSettingsUploader;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

import java.util.Arrays;
import java.util.List;

/**
 * Created by obetron on 21.11.2018
 */
public class GelecexSmartcardReader {

    public List<String> getTerminalList() throws GelecexSignerException {

        TubitakSettingsUploader.licenseFileUploader();
        try {
            String[] terminals = SmartOp.getCardTerminals();
            return Arrays.asList(terminals);
        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart hatası" ,e);
        }

    }

}

package com.gelecex.smartcard.manager;

import com.gelecex.smartcard.exception.SmartcardReaderException;
import com.gelecex.smartcard.model.Smartcard;

import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public interface EntityManager {

    List<Smartcard> getAllSc() throws SmartcardReaderException;
    void saveNewSc(Smartcard smartcard);
    void updateSc(Smartcard oldSmartcard, Smartcard newSmartcard);
    void deleteSc(Smartcard smartcard);

}

package com.gelecex.smartcard.manager;

import com.gelecex.smartcard.exception.SmartcardReaderException;
import com.gelecex.smartcard.model.Smartcard;
import org.w3c.dom.Document;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class XMLEntityManager implements EntityManager {

    private List<Smartcard> smartcardList;

    @Override
    public List<Smartcard> getAllSc() throws SmartcardReaderException {
        if(smartcardList != null && !smartcardList.isEmpty()) {
            return smartcardList;
        } else {
            throw new SmartcardReaderException("smartcardReaderError");
        }
    }

    @Override
    public void saveNewSc(Smartcard smartcard) {

    }

    @Override
    public void updateSc(Smartcard oldSmartcard, Smartcard newSmartcard) {

    }

    @Override
    public void deleteSc(Smartcard smartcard) {

    }

    private InputStream readScConfigFileAsStream() {
        InputStream scConfigStream = XMLEntityManager.class.getResourceAsStream("/smartcard-config.xml");
        return scConfigStream;
    }

    private void createXmlDocumentObject() {
        FileInputStream docStream = (FileInputStream) readScConfigFileAsStream();

    }

}

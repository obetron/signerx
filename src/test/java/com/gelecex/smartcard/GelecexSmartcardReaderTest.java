package com.gelecex.smartcard;

import com.gelecex.smartcard.GelecexSmartcardReader;
import com.gelecex.smartcard.exception.GelecexSignerException;
import org.junit.Assert;
import org.junit.Test;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;

import java.util.HashMap;
import java.util.List;

/**
 * Created by obetron on 21.11.2018
 */
public class GelecexSmartcardReaderTest {

//    private GelecexSmartcardReader gelecexSmartcardReader = new GelecexSmartcardReader();
//
//    @Test
//    public void getTerminalList() {
//        try {
//            List<String> terminalList = gelecexSmartcardReader.getTerminalList();
//            Assert.assertNotNull(terminalList);
//        } catch (GelecexSignerException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void getSmarcardObjectsTest() {
//        try {
//            List<String> terminalList = gelecexSmartcardReader.getTerminalList();
//            for (String terminal : terminalList) {
//                HashMap<Long, CardType> smartcardObjects = gelecexSmartcardReader.getSmartcardObjects(terminal);
//                Assert.assertNotNull(smartcardObjects);
//            }
//        } catch (GelecexSignerException e) {
//            e.printStackTrace();
//        }
//    }
}

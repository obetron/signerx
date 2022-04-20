package com.gelecex.signerx.service;

import com.gelecex.signerx.exception.SignerxXMLParserException;
import com.gelecex.signerx.service.SmartcardService;
import com.gelecex.signerx.service.SmartcardServiceImpl;
import com.gelecex.signerx.model.Smartcard;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.smartcardio.CardException;
import java.util.List;

/**
 * Created by obetron on 30.01.2019
 */
public class SmartcardServiceTest {

    private Logger LOGGER = Logger.getLogger(SmartcardServiceTest.class);

    @Test
    public void testReadSmartcard() throws CardException, SignerxXMLParserException {
        SmartcardService smartcardService = new SmartcardServiceImpl();
        List<Smartcard> smartcardList = smartcardService.readSmartcardToken();
        LOGGER.debug("Smartcard List Size: " + smartcardList.size());
        if (smartcardList.size() == 1) {
            if (smartcardList.get(0).getSmartcardLibraryList() != null
                    && smartcardList.get(0).getSmartcardLibraryList().get(0) != null) {
                Assert.assertEquals("akisp11", smartcardList.get(0).getSmartcardLibraryList().get(0).getName());
            }
        } else if (smartcardList.size() == 2) {
            if (smartcardList.get(1).getSmartcardLibraryList() != null
                    && smartcardList.get(1).getSmartcardLibraryList().get(0) != null) {
                Assert.assertEquals("eTPKCS11", smartcardList.get(1).getSmartcardLibraryList().get(0).getName());
            }
        } else {
            Assert.assertTrue(true);
        }
    }
}

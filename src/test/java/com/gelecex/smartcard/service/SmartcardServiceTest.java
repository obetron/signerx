package com.gelecex.smartcard.service;

import com.gelecex.smartcard.exception.XMLParserException;
import com.gelecex.smartcard.model.Smartcard;
import org.junit.Assert;
import org.junit.Test;

import javax.smartcardio.CardException;
import java.util.List;

/**
 * Created by obetron on 30.01.2019
 */
public class SmartcardServiceTest {

    @Test
    public void testReadSmartcard() throws CardException, XMLParserException {

        SmartcardService smartcardService = new SmartcardServiceImpl();
        List<Smartcard> smartcardList = smartcardService.readSmartcardToken();
        if(smartcardList.size() == 1) {
            Assert.assertEquals("eTPKCS11", smartcardList.get(0).getSmartcardLibraryList().get(0).getName());
        } else if(smartcardList.size() == 2) {
            Assert.assertEquals("akisp11", smartcardList.get(1).getSmartcardLibraryList().get(0).getName());
        } else {
            Assert.assertTrue(true);
        }
    }

}

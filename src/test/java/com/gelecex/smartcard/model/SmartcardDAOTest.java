package com.gelecex.smartcard.model;

import com.gelecex.smartcard.exception.SmartcardReaderException;
import com.gelecex.smartcard.exception.XMLParserException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class SmartcardDAOTest {

    @Test
    public void testGetSmartcardList() throws SmartcardReaderException, XMLParserException {
        SmartcardDao smartcard = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcard.getSmartcardList();
        Assert.assertEquals(5, smartcardList.size());
    }
}

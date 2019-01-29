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

    @Test
    public void testGetCardType() throws XMLParserException, SmartcardReaderException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(0);
        Assert.assertEquals("CARDOS", smartcard.getCardType());
    }

    @Test
    public void testGetLib() throws XMLParserException, SmartcardReaderException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(0);
        Assert.assertEquals("cmp11", smartcard.getSmartcardLibraryList().get(0).getName());
    }

    @Test
    public void testGetAtrValue() throws XMLParserException, SmartcardReaderException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(2);
        Assert.assertEquals("3BD5180081313A7D8073C8211030", smartcard.getAtrValueList().get(0));
    }

    @Test
    public void testGetArchValue() throws XMLParserException, SmartcardReaderException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(4);
        Assert.assertEquals("64", smartcard.getSmartcardLibraryList().get(1).getArch());
    }
}

package com.gelecex.signerx.model;

import com.gelecex.signerx.exception.SignerxSmartcardException;
import com.gelecex.signerx.exception.SignerxXMLParserException;
import com.gelecex.signerx.service.SmartcardXMLImpl;
import com.gelecex.signerx.service.SmartcardDao;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class SmartcardDAOTest {

    @Test
    public void testGetSmartcardList() throws SignerxSmartcardException, SignerxXMLParserException {
        SmartcardDao smartcard = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcard.getSmartcardList();
        Assert.assertEquals(6, smartcardList.size());
    }

    @Test
    public void testGetCardType() throws SignerxXMLParserException, SignerxSmartcardException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(0);
        Assert.assertEquals("CARDOS", smartcard.getCardType());
    }

    @Test
    public void testGetLib() throws SignerxXMLParserException, SignerxSmartcardException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(0);
        Assert.assertEquals("cmp11", smartcard.getSmartcardLibraryList().get(0).getName());
    }

    @Test
    public void testGetAtrValue() throws SignerxXMLParserException, SignerxSmartcardException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(2);
        Assert.assertEquals("3BD5180081313A7D8073C8211030", smartcard.getAtrValueList().get(0));
    }

    @Test
    public void testGetArchValue() throws SignerxXMLParserException, SignerxSmartcardException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        List<Smartcard> smartcardList = smartcardDao.getSmartcardList();
        Smartcard smartcard = smartcardList.get(4);
        Assert.assertEquals("64", smartcard.getSmartcardLibraryList().get(1).getArch());
    }

    @Test
    public void testGetSmartcard() throws SignerxXMLParserException {
        SmartcardDao smartcardDao = new SmartcardXMLImpl();
        Smartcard smartcard = smartcardDao.getSmartcard("3BD5180081313A7D8073C8211030");
        Assert.assertEquals("eTPKCS11", smartcard.getSmartcardLibraryList().get(0).getName());
    }
}

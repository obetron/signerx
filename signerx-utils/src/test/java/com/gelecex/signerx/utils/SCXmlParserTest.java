package com.gelecex.signerx.utils;

import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.common.smartcard.SmartcardAtr;
import com.gelecex.signerx.common.smartcard.SmartcardLibrary;
import com.gelecex.signerx.common.smartcard.SmartcardType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Created by obetron on 24.04.2022
 */
public class SCXmlParserTest {

    private SCXmlParser scDatabase;
    private List<SmartcardType> smartcardTypeList;

    @Before
    public void init() throws ParserConfigurationException, IOException, SAXException {
        scDatabase = new SCXmlParser();
        smartcardTypeList = scDatabase.readSmarcardDatabaseXml();
    }

    @Test
    public void testGetSmartcardDetailListNotNull() throws SignerxException, ParserConfigurationException, IOException, SAXException {
        Assert.assertNotNull(smartcardTypeList);
    }

    @Test
    public void testSmartcardTypeName() throws ParserConfigurationException, IOException, SAXException {
        SmartcardType smartcardType = smartcardTypeList.get(0);
        String smartcardName = smartcardType.getName();
        Assert.assertEquals("AEPKEYPER", smartcardName);
    }

    @Test
    public void testSmartcardLibName() {
        SmartcardType smartcardType = smartcardTypeList.get(0);
        List<SmartcardLibrary> libraryList = smartcardType.getLibraryList();
        SmartcardLibrary smartcardLibrary = libraryList.get(0);
        Assert.assertEquals("bp201w32HSM", smartcardLibrary.getName());
    }

    @Test
    public void testSmartcardLibArchVal() {
        SmartcardType smartcardType = smartcardTypeList.get(4);
        List<SmartcardLibrary> libraryList = smartcardType.getLibraryList();
        SmartcardLibrary smartcardLibrary = libraryList.get(0);
        Assert.assertEquals("32", smartcardLibrary.getArch());
    }

    @Test
    public void testSmartcardAtrVal() {
        SmartcardType smartcardType = smartcardTypeList.get(1);
        List<SmartcardAtr> atrList = smartcardType.getAtrList();
        SmartcardAtr smartcardAtr = atrList.get(0);
        Assert.assertEquals("3BBA11008131FE4D55454B41452056312E30AE", smartcardAtr.getValue());
    }
}

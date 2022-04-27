package com.gelecex.signerx.utils;

import com.gelecex.signerx.common.exception.SignerxException;
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
public class SCDatabaseTest {

    private SCDatabase scDatabase;

    @Before
    public void init() {
        scDatabase = new SCDatabase();
    }

    @Test
    public void testGetSmartcardDetailListNotNull() throws SignerxException, ParserConfigurationException, IOException, SAXException {
        scDatabase = new SCDatabase();
        List<SmartcardType> smartcardTypeList = scDatabase.readSmarcardDatabaseXml();
        Assert.assertNotNull(smartcardTypeList);
    }
}

package com.gelece.signer;

import com.gelecex.signer.GelecexSmartcardReader;
import com.gelecex.signer.exception.GelecexSignerException;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by obetron on 21.11.2018
 */
public class GelecexSmartcardReaderTest {

    @Test
    public void getTerminalList() {
        GelecexSmartcardReader gelecexSmartcardReader = new GelecexSmartcardReader();
        try {
            List<String> terminalList = gelecexSmartcardReader.getTerminalList();
            System.out.println(terminalList.get(0));
            Assert.assertNotNull(terminalList);
        } catch (GelecexSignerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSmarcardObjectsTest() {
        GelecexSmartcardReader gelecexSmartcardReader = new GelecexSmartcardReader();
        String terminalTestValue = "ACS ACR 38U-CCID";
        try {
            gelecexSmartcardReader.getSmartcardObjects(terminalTestValue);
        } catch (GelecexSignerException e) {
            e.printStackTrace();
        }
    }
}

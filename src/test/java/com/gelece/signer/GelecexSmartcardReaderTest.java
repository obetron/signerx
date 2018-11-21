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
            Assert.assertNotNull(terminalList);
        } catch (GelecexSignerException e) {
            e.printStackTrace();
        }

    }

}

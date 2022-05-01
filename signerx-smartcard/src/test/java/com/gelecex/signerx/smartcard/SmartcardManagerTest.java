package com.gelecex.signerx.smartcard;

import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.common.smartcard.SignerxSmartcard;
import org.junit.Test;

import java.util.List;

/**
 * Created by obetron on 1.05.2022
 */
public class SmartcardManagerTest {

    @Test
    public void getPluggedSmartcardListTest() throws SignerxException {
        SmartcardManager smartcardManager = new SmartcardManagerImpl();
        List<SignerxSmartcard> pluggedSmartcardList = smartcardManager.getPluggedSmartcardList();
    }

}

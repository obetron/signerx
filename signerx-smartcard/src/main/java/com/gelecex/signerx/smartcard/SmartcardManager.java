package com.gelecex.signerx.smartcard;

import com.gelecex.signerx.common.exception.SignerxException;

/**
 * Created by obetron on 27.04.2022
 */
public interface SmartcardManager {

    String getAtrFromSmartcard() throws SignerxException;
    void detectSmartcardLib(String atrValue) throws SignerxException;

}

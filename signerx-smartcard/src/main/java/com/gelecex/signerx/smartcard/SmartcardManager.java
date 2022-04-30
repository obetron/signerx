package com.gelecex.signerx.smartcard;

import com.gelecex.signerx.common.exception.SignerxException;

/**
 * Created by obetron on 27.04.2022
 */
public interface SmartcardManager {

    void detectSmartcards() throws SignerxException;

}

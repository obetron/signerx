package com.gelecex.signerx.smartcard;

import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.common.smartcard.SignerxSmartcard;

import java.util.List;

/**
 * Created by obetron on 27.04.2022
 */
public interface SmartcardManager {

    List<SignerxSmartcard> getPluggedSmartcardList() throws SignerxException;

}

package com.gelecex.signerx.common.signature;

import com.gelecex.signerx.common.exception.SignerxException;

/**
 * Created by obetron on 6.06.2022
 */
public interface BaseSigner {

    byte[] createSignature(byte[] dataToBeSigned) throws SignerxException;

}

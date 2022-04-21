package com.gelecex.signerx.cades;

import com.gelecex.signerx.common.EnumSignatureType;

/**
 * @author obetron
 * created on 21.04.2022
 */

public interface CadesSignerx {

    byte[] sign(byte[] dataToBeSigned, EnumSignatureType signatureType);

}

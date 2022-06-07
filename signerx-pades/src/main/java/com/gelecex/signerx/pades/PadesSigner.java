package com.gelecex.signerx.pades;

import com.gelecex.signerx.common.signature.BaseSigner;
import com.gelecex.signerx.pades.model.PadesSignature;

/**
 * Created by obetron on 6.06.2022
 */
public class PadesSigner implements BaseSigner {

    private PadesSignature padesSignature;

    private PadesSigner(){}

    public PadesSigner(PadesSignature padesSignature) {
        this.padesSignature = padesSignature;
    }

    @Override
    public byte[] createSignature() {
        //TODO: 1. check the type of byte array is really pdf?
        //TODO: 2. sign byte array pdf in pades format.
        return new byte[0];
    }
}

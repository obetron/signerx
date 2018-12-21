package com.gelecex.smartcard;

import tr.gov.tubitak.uekae.esya.api.cmssignature.ISignable;
import tr.gov.tubitak.uekae.esya.api.crypto.alg.DigestAlg;
import tr.gov.tubitak.uekae.esya.api.crypto.exceptions.CryptoException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by obetron on 18.11.2018
 */
public class GelecexSignableHash implements ISignable {

    private byte[] hashToBeSigned;

    public GelecexSignableHash(byte[] hashToBeSigned) {
        this.hashToBeSigned = hashToBeSigned;
    }

    @Override
    public byte[] getContentData() {
        return hashToBeSigned;
    }

    @Override
    public byte[] getMessageDigest(DigestAlg digestAlg) throws CryptoException, IOException {
        return hashToBeSigned;
    }

    @Override
    public InputStream getAsInputStream() throws IOException {
        return null;
    }
}

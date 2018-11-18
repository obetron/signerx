package com.gelecex.signer;

import com.gelecex.signer.exception.GelecexSignerException;
import com.gelecex.signer.utils.TubitakSettingsUploader;
import tr.gov.tubitak.uekae.esya.api.cmssignature.ISignable;
import tr.gov.tubitak.uekae.esya.api.cmssignature.SignableByteArray;

/**
 * Created by ebasaran on 14.11.2018.
 */
public class GelecexSigner {

    private static GelecexSigner instance = null;

    private GelecexSigner() throws GelecexSignerException {
        TubitakSettingsUploader.licenseFileUploader();
    }

    public static GelecexSigner getInstance() throws GelecexSignerException {
        if(instance == null) {
            instance = new GelecexSigner();
        }
        return instance;
    }

    /**
     * Bu metot imzalanacak veriyi alir ve ozetini hesaplar sonrasinda imzalar.
     * @param bytes imzalanacak veri.
     */
    public void signValue(byte[] bytes) {
        ISignable contentToBeSigned = new SignableByteArray(bytes);
    }

    /**
     * Bu metot imzalanacak degerin ozet halini alir ve imzalar.
     * @param bytes imzalanacak verinin ozet degeri.
     */
    public void signHashValue(byte[] bytes) {
        ISignable contentToBeSigned = new GelecexSignableHash(bytes);

    }
}

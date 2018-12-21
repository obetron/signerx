package com.gelecex.smartcard;


import org.apache.log4j.Logger;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;

import java.security.cert.X509Certificate;

/**
 * Created by obetron on 23.11.2018.
 */
public class GelecexCertificate {

    private static final Logger LOGGER = Logger.getLogger(GelecexCertificate.class);

    private ECertificate signerCertificate;
    private X509Certificate x509Certificate;

    public ECertificate getSignerCertificate() {
        return signerCertificate;
    }

    protected void setSignerCertificate(ECertificate signerCertificate) {
        this.signerCertificate = signerCertificate;
    }

    public X509Certificate getX509Certificate() {
        return x509Certificate;
    }

    protected void setX509Certificate(X509Certificate x509Certificate) {
        this.x509Certificate = x509Certificate;
    }
}

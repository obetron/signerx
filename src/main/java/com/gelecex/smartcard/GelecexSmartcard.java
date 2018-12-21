package com.gelecex.smartcard;

import org.apache.log4j.Logger;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;

/**
 * Created by obetron on 23.11.2018.
 */
public class GelecexSmartcard {

    private static final Logger LOGGER = Logger.getLogger(GelecexSmartcard.class);

    private Long smartcardSlot;
    private CardType smartcardType;
    private GelecexCertificate gelecexCertificate;

    public Long getSmartcardSlot() {
        return smartcardSlot;
    }

    protected void setSmartcardSlot(Long smartcardSlot) {
        this.smartcardSlot = smartcardSlot;
    }

    public CardType getSmartcardType() {
        return smartcardType;
    }

    protected void setSmartcardType(CardType smartcardType) {
        this.smartcardType = smartcardType;
    }

    public GelecexCertificate getGelecexCertificate() {
        return gelecexCertificate;
    }

    protected void setGelecexCertificate(GelecexCertificate gelecexCertificate) {
        this.gelecexCertificate = gelecexCertificate;
    }
}

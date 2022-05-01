package com.gelecex.signerx.common.smartcard;

/**
 * Created by obetron on 1.05.2022
 */
public class SignerxSmartcard {

    private String cardName;
    private SignerxCertificate certificate;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public SignerxCertificate getCertificate() {
        return certificate;
    }

    public void setCertificate(SignerxCertificate certificate) {
        this.certificate = certificate;
    }
}

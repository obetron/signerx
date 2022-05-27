package com.gelecex.signerx.common.smartcard;

import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by obetron on 1.05.2022
 */
public class SignerxSmartcard {

    private String cardName;
    private String cardLibName;
    private List<SignerxCertificate> certificateInfos;
    private List<X509Certificate> certificateList;

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardLibName() {
        return cardLibName;
    }

    public void setCardLibName(String cardLibName) {
        this.cardLibName = cardLibName;
    }

    public List<SignerxCertificate> getCertificateInfos() {
        return certificateInfos;
    }

    public void setCertificateInfos(List<SignerxCertificate> certificateInfos) {
        this.certificateInfos = certificateInfos;
    }

    public List<X509Certificate> getCertificateList() {
        return certificateList;
    }

    public void setCertificateList(List<X509Certificate> certificateList) {
        this.certificateList = certificateList;
    }
}

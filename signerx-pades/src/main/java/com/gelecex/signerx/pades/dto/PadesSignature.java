package com.gelecex.signerx.pades.dto;

/**
 * Created by obetron on 7.06.2022
 */
public class PadesSignature {

    private String name;
    private String location;
    private String reason;
    private String contanctInfo;
    private String signatureDate;
    private byte[] content;
    private byte[] signedContent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContanctInfo() {
        return contanctInfo;
    }

    public void setContanctInfo(String contanctInfo) {
        this.contanctInfo = contanctInfo;
    }

    public String getSignatureDate() {
        return signatureDate;
    }

    public void setSignatureDate(String signatureDate) {
        this.signatureDate = signatureDate;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getSignedContent() {
        return signedContent;
    }

    public void setSignedContent(byte[] signedContent) {
        this.signedContent = signedContent;
    }
}

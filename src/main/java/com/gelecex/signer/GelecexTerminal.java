package com.gelecex.signer;

import org.apache.log4j.Logger;

/**
 * Created by obetron on 23.11.2018.
 */
public class GelecexTerminal {

    private static final Logger LOGGER = Logger.getLogger(GelecexTerminal.class);
    private String terminalName;
    private GelecexSmartcard gelecexSmartcard;
    private GelecexCertificate gelecexCertificate;

    public String getTerminalName() {
        return terminalName;
    }

    protected void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }

    public GelecexSmartcard getGelecexSmartcard() {
        return gelecexSmartcard;
    }

    protected void setGelecexSmartcard(GelecexSmartcard gelecexSmartcard) {
        this.gelecexSmartcard = gelecexSmartcard;
    }

    public GelecexCertificate getGelecexCertificate() {
        return gelecexCertificate;
    }

    protected void setGelecexCertificate(GelecexCertificate gelecexCertificate) {
        this.gelecexCertificate = gelecexCertificate;
    }
}

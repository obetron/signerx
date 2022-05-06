package com.gelecex.signerx.smartcard;

/**
 * Created by obetron on 27.04.2022
 */

import com.gelecex.signerx.common.EnumOsArch;
import com.gelecex.signerx.common.EnumOsName;
import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.common.smartcard.SignerxSmartcard;
import com.gelecex.signerx.common.smartcard.SmartcardAtr;
import com.gelecex.signerx.common.smartcard.SmartcardLibrary;
import com.gelecex.signerx.common.smartcard.SmartcardType;
import com.gelecex.signerx.utils.SCXmlParser;
import com.gelecex.signerx.utils.SignerxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.pkcs11.wrapper.PKCS11;
import sun.security.pkcs11.wrapper.PKCS11Constants;
import sun.security.pkcs11.wrapper.PKCS11Exception;

import javax.smartcardio.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmartcardManagerImpl implements SmartcardManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartcardManagerImpl.class);

    @Override
    public List<SignerxSmartcard> getPluggedSmartcardList() throws SignerxException {
        List<SignerxSmartcard> signerxSmartcardList = new ArrayList<>();
        try {
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals cardTerminals = terminalFactory.terminals();
            List<CardTerminal> cardTerminalList = cardTerminals.list();
            for (CardTerminal cardTerminal : cardTerminalList) {
                Card card = cardTerminal.connect("*");
                String atrValue = SignerxUtils.byteToHex(card.getATR().getBytes());
                String libName = detectSmartcardLib(atrValue);
                String staticMacLibPath = "/usr/local/lib/lib" +libName + getSystemExtension();
                connectToSmartcard(staticMacLibPath);
            }
            if(cardTerminalList.size() == 0) {
                LOGGER.warn("Bilgisayarda takili akilli kart bulunamadi!");
            }
        } catch (CardException e) {
            throw new SignerxException("Sistemde takili olan terminaller alinirken hata olustu!", e);
        }
        return signerxSmartcardList;
    }

    private void connectToSmartcard(String smartcardLibPath) {
        try {
            PKCS11 pkcs11 = PKCS11.getInstance(smartcardLibPath, "C_GetFunctionList", null, false);
            long session = pkcs11.C_OpenSession(0, PKCS11Constants.CKF_SERIAL_SESSION, null, null);
            System.out.println("Session: " + session);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (PKCS11Exception e) {
            throw new RuntimeException(e);
        }

    }

    private List<String> getAtrFromSmartcards() throws SignerxException {
        try {
            List<String> smartcardAtrList = new ArrayList<>();
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals terminals = terminalFactory.terminals();
            List<CardTerminal> cardTerminalList = terminals.list();
            for (CardTerminal cardTerminal : cardTerminalList) {
                Card card = cardTerminal.connect("T0");
                ATR atr = card.getATR();
                byte[] atrBytes = atr.getBytes();
                smartcardAtrList.add( SignerxUtils.byteToHex(atrBytes));
            }
            return smartcardAtrList;
        } catch (CardException e) {
            throw new SignerxException("Terminal listesi alinirken hata olustu!", e);
        }
    }

    private String detectSmartcardLib(String atrValue) throws SignerxException {
        List<SmartcardLibrary> smartcardLibraryList = getSmartcardLibraryList(atrValue);
        if(smartcardLibraryList.size() > 1) {
            EnumOsArch osArch = detectSystemArch();
            for (SmartcardLibrary smartcardLibrary : smartcardLibraryList) {
                if(osArch.toString().equalsIgnoreCase(smartcardLibrary.getArch())) {
                    return smartcardLibrary.getName();
                }
            }
        } else {
            return smartcardLibraryList.get(0).getName();
        }
        return null;
    }

    private List<SmartcardLibrary> getSmartcardLibraryList(String atrValue) throws SignerxException {
        SCXmlParser xmlParser = new SCXmlParser();
        List<SmartcardType> smartcardTypeList = xmlParser.readSmarcardDatabaseXml();
        for (SmartcardType smartcardType : smartcardTypeList) {
            List<SmartcardAtr> atrList = smartcardType.getAtrList();
            for (SmartcardAtr smartcardAtr : atrList) {
                if(smartcardAtr.getValue().equalsIgnoreCase(atrValue))
                {
                    return smartcardType.getLibraryList();
                }
            }
        }
        LOGGER.error("ATR Degeri: " + atrValue + " - degeri icin kayit bulunamadi!");
        LOGGER.error("ATR degerini elle scdatabase.xml dosyasina ekleyebilirsiniz!");
        return null;
    }

    private EnumOsArch detectSystemArch() {
        String osArch = System.getProperty("os.arch");
        if(osArch.contains("64")) {
            return EnumOsArch.x64;
        }
        return EnumOsArch.x32;
    }

    private String getSystemExtension() throws SignerxException {
        String osName = System.getProperty("os.name");
        if (osName.contains(EnumOsName.Windows.name())) {
            return ".dll";
        } else if (osName.contains(EnumOsName.Linux.name())) {
            return ".so";
        } else if (osName.contains(EnumOsName.Mac.name())) {
            return ".dylib";
        } else {
            throw new SignerxException("Bilinmeyen isletim sistemi!");
        }
    }
}

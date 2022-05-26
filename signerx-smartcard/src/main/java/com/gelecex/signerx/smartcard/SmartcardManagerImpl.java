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
import sun.security.pkcs11.wrapper.*;

import javax.smartcardio.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SmartcardManagerImpl implements SmartcardManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartcardManagerImpl.class);

    static {
        String osName = System.getProperty("os.name");
        if(osName.contains(EnumOsName.Mac.name())) {
            System.setProperty("sun.security.smartcardio.library", "/System/Library/Frameworks/PCSC.framework/Versions/Current/PCSC");
        } else if(osName.contains(EnumOsName.Windows.name())) {
            clearSmartcardCache();
        }
    }

    @Override
    public List<SignerxSmartcard> getPluggedSmartcardList() throws SignerxException {
        List<SignerxSmartcard> signerxSmartcardList = new ArrayList<>();
        try {
            TerminalFactory terminalFactory = TerminalFactory.getDefault();
            CardTerminals cardTerminals = terminalFactory.terminals();
            List<CardTerminal> cardTerminalList = cardTerminals.list(CardTerminals.State.CARD_PRESENT);
            if(cardTerminalList.size() == 0) {
                LOGGER.warn("Bilgisayarda takili akilli kart bulunamadi!");
            } else {
                LOGGER.info(cardTerminalList.size() + " adet takili akilli kart tespit edildi.");
                for (CardTerminal cardTerminal : cardTerminalList) {
                    Card card = cardTerminal.connect("*");
                    String atrValue = SignerxUtils.byteToHex(card.getATR().getBytes());
                    String libName = getPluggedSmartcardLibName(atrValue);
                    String libPath = SmartcardManagerImpl.class.getClassLoader().getResource(libName).getPath();
                    LOGGER.debug("Surucu Kutuphane Yolu: " + libPath);
                    connectToSmartcard(libPath, cardTerminal.getName());
                }
            }
        } catch (CardException e) {
            throw new SignerxException("Sistemde takili olan terminaller alinirken hata olustu!", e);
        }
        return signerxSmartcardList;
    }

    private void connectToSmartcard(String smartcardLibPath, String terminalName) throws SignerxException {
        try {
            long slotIndex = 1L;
            PKCS11 pkcs11 = PKCS11.getInstance(smartcardLibPath, "C_GetFunctionList", null, false);
            long[] slotList = pkcs11.C_GetSlotList(true);
            for (long s : slotList) {
                char NULL_CHAR = '\0';
                CK_SLOT_INFO slotInfo = pkcs11.C_GetSlotInfo(s);
                String str = new String(slotInfo.slotDescription).trim();
                if (str.indexOf(NULL_CHAR) > 0) {
                    str = str.substring(0, str.indexOf(NULL_CHAR));
                }
                if (terminalName.contains(str)) {
                    slotIndex = s;
                }
            }
            long sessionId = pkcs11.C_OpenSession(slotIndex, PKCS11Constants.CKF_SERIAL_SESSION, null, null);
            CK_SESSION_INFO sessionInfo = pkcs11.C_GetSessionInfo(sessionId);
            LOGGER.debug("Session Info: " + sessionInfo.toString());
            LOGGER.debug("Session Id: " + sessionId);
        } catch (PKCS11Exception e) {
            throw new SignerxException("PKCS11 islemleri sirasinda bir hata olustu!", e);
        } catch (IOException e) {
            throw new SignerxException("Surucu kutuphanesi kullanilarak PKCS11 nesnesi olusturulurken hata olustu!", e);
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

    private String getPluggedSmartcardLibName(String atrValue) throws SignerxException {
        String osName = System.getProperty("os.name");
        String libName = detectSmartcardLib(atrValue);
        if(osName.contains(EnumOsName.Mac.name()) || osName.contains(EnumOsName.Linux.name())) {
            return "lib" + libName + getSystemExtension();
        } else {
            return libName + getSystemExtension();
        }
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

    private static void clearSmartcardCache() {
        try {
            Class pcscterminal = null;
            pcscterminal = Class.forName("sun.security.smartcardio.PCSCTerminals");
            Field contextId = pcscterminal.getDeclaredField("contextId");
            contextId.setAccessible(true);

            if (contextId.getLong(pcscterminal) != 0L) {
                // First get a new context value
                Class pcsc = Class.forName("sun.security.smartcardio.PCSC");
                Method SCardEstablishContext = pcsc.getDeclaredMethod(
                        "SCardEstablishContext",
                        new Class[]{Integer.TYPE}
                );
                SCardEstablishContext.setAccessible(true);

                Field SCARD_SCOPE_USER = pcsc.getDeclaredField("SCARD_SCOPE_USER");
                SCARD_SCOPE_USER.setAccessible(true);

                long newId = ((Long) SCardEstablishContext.invoke(pcsc,
                        new Object[]{SCARD_SCOPE_USER.getInt(pcsc)}
                ));
                contextId.setLong(pcscterminal, newId);
                // Then clear the terminals in cache
                TerminalFactory factory = TerminalFactory.getDefault();
                CardTerminals terminals = factory.terminals();
                Field fieldTerminals = pcscterminal.getDeclaredField("terminals");
                fieldTerminals.setAccessible(true);
                Class classMap = Class.forName("java.util.Map");
                Method clearMap = classMap.getDeclaredMethod("clear");

                clearMap.invoke(fieldTerminals.get(terminals));
            }
        } catch(ClassNotFoundException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e){
            LOGGER.error("Window isletim sistemi icin smartcard sinifi cache temizligi sirasinda hata meydana geldi!", e);
        }
    }
}

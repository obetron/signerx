package com.gelecex.signerx.smartcard;

/**
 * Created by obetron on 27.04.2022
 */

import com.gelecex.signerx.common.EnumOsArch;
import com.gelecex.signerx.common.EnumOsName;
import com.gelecex.signerx.common.exception.SignerxException;
import com.gelecex.signerx.common.smartcard.*;
import com.gelecex.signerx.utils.SCXmlParser;
import com.gelecex.signerx.utils.SignerxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.security.pkcs11.wrapper.*;

import javax.smartcardio.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class SmartcardManagerImpl implements SmartcardManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartcardManagerImpl.class);
    private static final CK_ATTRIBUTE CLASS_CERTIFICATE_ATTR = new CK_ATTRIBUTE(PKCS11Constants.CKA_CLASS, PKCS11Constants.CKO_CERTIFICATE);
    private static final CK_ATTRIBUTE TOKEN_ATTR = new CK_ATTRIBUTE(PKCS11Constants.CKA_TOKEN, PKCS11Constants.TRUE);

    static {
        String osName = System.getProperty("os.name");
        if(osName.contains(EnumOsName.Mac.name())) {
            System.setProperty("sun.security.smartcardio.library", "/System/Library/Frameworks/PCSC.framework/Versions/Current/PCSC");
        } else if(osName.contains(EnumOsName.Windows.name())) {
            SmartcardUtils.clearSmartcardCache();
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
                    SignerxSmartcard signerxSmartcard = new SignerxSmartcard();
                    signerxSmartcard.setCardName(cardTerminal.getName());
                    Card card = cardTerminal.connect("*");
                    String atrValue = SignerxUtils.byteToHex(card.getATR().getBytes());
                    String libName = getPluggedSmartcardLibName(atrValue);
                    ClassLoader classLoader = SmartcardManagerImpl.class.getClassLoader();
                    URL resourceUrl = classLoader.getResource(libName);
                    String libPath = resourceUrl.getPath();
                    signerxSmartcard.setCardLibName(libPath);
                    LOGGER.debug("Surucu Kutuphane Yolu: " + libPath);
                    openSessionOnSmartcard(signerxSmartcard);
                    signerxSmartcardList.add(signerxSmartcard);
                }
            }
        } catch (CardException e) {
            throw new SignerxException("Sistemde takili olan terminaller alinirken hata olustu!", e);
        }
        return signerxSmartcardList;
    }

    private void openSessionOnSmartcard(SignerxSmartcard signerxSmartcard) throws SignerxException {
        try {
            long slotIndex = 1L;
            PKCS11 pkcs11 = PKCS11.getInstance(signerxSmartcard.getCardLibName(), "C_GetFunctionList", null, false);
            long[] slotList = pkcs11.C_GetSlotList(true);
            for (long s : slotList) {
                char nullChar = '\0';
                CK_SLOT_INFO slotInfo = pkcs11.C_GetSlotInfo(s);
                String str = new String(slotInfo.slotDescription).trim();
                if (str.indexOf(nullChar) > 0) {
                    str = str.substring(0, str.indexOf(nullChar));
                }
                if (signerxSmartcard.getCardName().contains(str)) {
                    slotIndex = s;
                }
            }
            long sessionId = pkcs11.C_OpenSession(slotIndex, PKCS11Constants.CKF_SERIAL_SESSION, null, null);
            CK_SESSION_INFO sessionInfo = pkcs11.C_GetSessionInfo(sessionId);
            LOGGER.debug("Session Info: " + sessionInfo.toString());
            LOGGER.debug("Session Id: " + sessionId);
            List<X509Certificate> certificates = getSmartcardCertificates(pkcs11, sessionId);
            signerxSmartcard.setCertificateList(certificates);
            signerxSmartcard.setCertificateInfos(getSignerxCertificateList(certificates));
        } catch (PKCS11Exception e) {
            throw new SignerxException("PKCS11 islemleri sirasinda bir hata olustu!", e);
        } catch (IOException e) {
            throw new SignerxException("Surucu kutuphanesi kullanilarak PKCS11 nesnesi olusturulurken hata olustu!", e);
        }
    }

    private List<SignerxCertificate> getSignerxCertificateList(List<X509Certificate> certificateList) {
        List<SignerxCertificate> signerxCertificateList = new ArrayList<>();
        for (X509Certificate x509Certificate : certificateList) {
            SignerxCertificate signerxCertificate = new SignerxCertificate();
            Principal subjectPrinciple = x509Certificate.getSubjectDN();
            Principal issuerPrincipal = x509Certificate.getIssuerDN();
            signerxCertificate.setCommonName(getCommonNameFromDN(subjectPrinciple));
            signerxCertificate.setTckno(getTcKNo(subjectPrinciple));
            signerxCertificate.setIssuerName(getCommonNameFromDN(issuerPrincipal));
            signerxCertificate.setSerialNumber(x509Certificate.getSerialNumber().toString());
            signerxCertificate.setNotBefore(x509Certificate.getNotBefore());
            signerxCertificate.setNotAfter(x509Certificate.getNotAfter());
            signerxCertificateList.add(signerxCertificate);
        }
        return signerxCertificateList;
    }

    private String getCommonNameFromDN(Principal principal) {
        String principalStr = principal.getName();
        return principalStr.substring(principalStr.indexOf("CN=")+3, principalStr.indexOf(","));
    }

    private String getTcKNo(Principal principal) {
        String principalStr = principal.getName();
        return principalStr.substring(principalStr.indexOf("SERIALNUMBER=")+13, principalStr.indexOf("SERIALNUMBER")+24);
    }

    private List<X509Certificate> getSmartcardCertificates(PKCS11 pkcs11, long sessionId) throws SignerxException {
        try {
            List<X509Certificate> certificateList = new ArrayList<>();
            CK_ATTRIBUTE[] ckAttributeTemplates = {CLASS_CERTIFICATE_ATTR, TOKEN_ATTR};
            pkcs11.C_FindObjectsInit(sessionId, ckAttributeTemplates);
            long[] availableCertificates = pkcs11.C_FindObjects(sessionId, 10L);
            if(availableCertificates == null) {
                throw new SignerxException("Akilli kart icerisinden sertifikalar alinirken hata olustu!");
            }
            for (long availableCertificate : availableCertificates) {
                CK_ATTRIBUTE[] template = new CK_ATTRIBUTE[1];
                template[0] = new CK_ATTRIBUTE();
                template[0].type = PKCS11Constants.CKA_VALUE;
                pkcs11.C_GetAttributeValue(sessionId, availableCertificate, template);
                byte[] derEncodedCertificate = (byte[]) template[0].pValue;
                InputStream certStream = new ByteArrayInputStream(derEncodedCertificate);
                CertificateFactory factory = CertificateFactory.getInstance("X.509");
                X509Certificate signerCert = (X509Certificate) factory.generateCertificate(certStream);
                certificateList.add(signerCert);
                LOGGER.debug("Sertifika: " + signerCert.getSubjectDN().getName());
            }
            return certificateList;
        } catch (PKCS11Exception | CertificateException e) {
            throw new SignerxException("Akilli karttan sertifikalar cekilirken hata olustu!", e);
        }
    }

    private String detectSmartcardLib(String atrValue) throws SignerxException {
        List<SmartcardLibrary> smartcardLibraryList = getSmartcardLibraryList(atrValue);
        if(smartcardLibraryList != null && smartcardLibraryList.size() > 1) {
            EnumOsArch osArch = SmartcardUtils.detectSystemArch();
            for (SmartcardLibrary smartcardLibrary : smartcardLibraryList) {
                if(osArch.toString().equalsIgnoreCase(smartcardLibrary.getArch())) {
                    return smartcardLibrary.getName();
                }
            }
        } else {
            if(smartcardLibraryList != null && smartcardLibraryList.get(0) != null) {
                return smartcardLibraryList.get(0).getName();
            }
        }
        return null;
    }

    private List<SmartcardLibrary> getSmartcardLibraryList(String atrValue) throws SignerxException {
        SCXmlParser xmlParser = new SCXmlParser();
        List<SmartcardType> smartcardTypeList = xmlParser.readSmarcardDatabaseXml();
        for (SmartcardType smartcardType : smartcardTypeList) {
            List<SmartcardAtr> atrList = smartcardType.getAtrList();
            for (SmartcardAtr smartcardAtr : atrList) {
                if(smartcardAtr.getValue().equalsIgnoreCase(atrValue)) {
                    return smartcardType.getLibraryList();
                }
            }
        }
        String log = "ATR Degeri: " + atrValue + " - degeri icin kayit bulunamadi!";
        log += "\nATR degerini elle scdatabase.xml dosyasina ekleyebilirsiniz!";
        LOGGER.error(log);
        return new ArrayList<>();
    }

    private String getPluggedSmartcardLibName(String atrValue) throws SignerxException {
        String osName = System.getProperty("os.name");
        String libName = detectSmartcardLib(atrValue);
        if(osName.contains(EnumOsName.Mac.name()) || osName.contains(EnumOsName.Linux.name())) {
            return "lib" + libName + SmartcardUtils.getSystemExtension();
        } else {
            return libName + SmartcardUtils.getSystemExtension();
        }
    }
}

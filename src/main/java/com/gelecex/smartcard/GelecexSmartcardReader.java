package com.gelecex.smartcard;

import com.gelecex.smartcard.exception.GelecexSignerException;
import com.gelecex.smartcard.utils.TubitakSettingsUploader;
import org.apache.log4j.Logger;
import sun.security.pkcs11.wrapper.PKCS11Exception;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.util.bag.Pair;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.CardType;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCard;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartCardException;
import tr.gov.tubitak.uekae.esya.api.smartcard.pkcs11.SmartOp;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by obetron on 21.11.2018
 */
public class GelecexSmartcardReader {

    private static final Logger LOGGER = Logger.getLogger(GelecexSmartcardReader.class);

    public List<GelecexTerminal> getTerminalList() throws GelecexSignerException {
        List<GelecexTerminal> gelecexTerminalList = new ArrayList<>();
        List<String> gelecexTerminals = getTerminals();
        for (String glcxTerminal : gelecexTerminals) {
            GelecexTerminal gelecexTerminal = new GelecexTerminal();
            gelecexTerminal.setTerminalName(glcxTerminal);

        }
        return gelecexTerminalList;
    }

    private List<String> getTerminals() throws GelecexSignerException {
        TubitakSettingsUploader.licenseFileUploader();
        try {
            String[] terminals = SmartOp.getCardTerminals();
            LOGGER.debug(terminals.length + " adet takili terminal bulundu.");
            return Arrays.asList(terminals);
        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart hatası" ,e);
        }

    }

    private Pair<Long, CardType> getSmartcardObjects(String terminal) throws GelecexSignerException {
        TubitakSettingsUploader.licenseFileUploader();
        try {
            Pair<Long, CardType> smartcard = SmartOp.getSlotAndCardType(terminal);
            LOGGER.debug("Akıllı kart slot ve kart tipi değerleri alındı.");
            return smartcard;
        } catch (SmartCardException e) {
            throw new GelecexSignerException("Akıllı kart nesneleri alınırken hata oluştu!", e);
        }
    }

    private long openSessionOnSmartcard(Pair<Long, CardType> smartcardObjects) throws GelecexSignerException {
        try {
            Long smartcardSlot = smartcardObjects.getObject1();
            CardType smartcardType = smartcardObjects.getObject2();
            SmartCard smartcard = new SmartCard(smartcardType);
            long smartcardSession = smartcard.openSession(smartcardSlot);
            LOGGER.debug("Akıllı kart üzerinde session açıldı.");
            return smartcardSession;
        } catch (PKCS11Exception e) {
            throw new GelecexSignerException("Akıllı kart PKCS11 hatası.", e);
        } catch (IOException e) {
            throw new GelecexSignerException("Akıllı kart IOException hatası.", e);
        }
    }

    private List<GelecexCertificate> getCertificateList(SmartCard smartcard, long sessionId) throws GelecexSignerException {
            List<GelecexCertificate> gelecexCertificateList = new ArrayList<>();
        try {
            List<byte[]> smartcardCertificates =  smartcard.getSignatureCertificates(sessionId);
            for (byte[] smartcardCertificate : smartcardCertificates) {
                try {
                    GelecexCertificate gelecexCertificate = new GelecexCertificate();
                    ECertificate eCertificate = new ECertificate(smartcardCertificate);
                    X509Certificate x509Certificate = eCertificate.asX509Certificate();
                    gelecexCertificate.setSignerCertificate(eCertificate);
                    gelecexCertificate.setX509Certificate(x509Certificate);
                    gelecexCertificateList.add(gelecexCertificate);
                } catch (ESYAException e) {
                    throw new GelecexSignerException("bytearray ECertificate dönüştürme hatası!", e);
                }
            }
        } catch (PKCS11Exception e) {
            throw new GelecexSignerException("Akıllı kart sertifika okumada PKCS11 hatası!", e);
        } catch (SmartCardException e) {
            throw new GelecexSignerException("Sertifika okunurken akıllı kart hatası!", e);
        }
        return gelecexCertificateList;
    }
}

package com.gelecex.smartcard.utils;

import com.gelecex.smartcard.exception.GelecexSignerException;
import org.apache.log4j.Logger;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;

import java.io.InputStream;

/**
 * Created by obetron on 17.11.2018
 */
public class TubitakSettingsUploader {

    private static final Logger LOGGER = Logger.getLogger(TubitakSettingsUploader.class);

    private static InputStream licenseFileReader() throws GelecexSignerException {
        InputStream licenseFileStream = TubitakSettingsUploader.class.getResourceAsStream("/lisans.xml");
        if(licenseFileStream != null) {
            LOGGER.debug("Tubitak Lisans dosyasi okuma basarili.");
            return licenseFileStream;
        } else {
             throw new GelecexSignerException("Tubitak Lisans dosyasi okuma hatasi!");
        }
    }

    public static void licenseFileUploader() throws GelecexSignerException {
        try {
            InputStream licenseFileStream = licenseFileReader();
            LicenseUtil.setLicenseXml(licenseFileStream);
            LOGGER.debug("Tubitak Lisans dosyasi yukleme basarili.");
        } catch (ESYAException e) {
            throw new GelecexSignerException("Tubitak Lisans dosyasi yukleme hatasi!" , e);
        }
    }

}

package com.gelecex.smartcard.utils;

import com.gelecex.smartcard.exception.GelecexSignerException;
import org.apache.log4j.Logger;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;

import java.io.InputStream;

/**
 * Created by obetron on 17.11.2018
 */
public class ConfigurationUploader {

    private static final Logger LOGGER = Logger.getLogger(ConfigurationUploader.class);

    private static InputStream ma3LicenseFileReader() throws GelecexSignerException {
        InputStream licenseFileStream = ConfigurationUploader.class.getResourceAsStream("/lisans.xml");
        if(licenseFileStream != null) {
            LOGGER.debug("Tubitak Lisans dosyasi okuma basarili.");
            return licenseFileStream;
        } else {
             throw new GelecexSignerException("Tubitak Lisans dosyasi okuma hatasi!");
        }
    }

    public static void licenseFileUploader() throws GelecexSignerException {
        try {
            InputStream licenseFileStream = ma3LicenseFileReader();
            LicenseUtil.setLicenseXml(licenseFileStream);
            LOGGER.debug("Tubitak Lisans dosyasi yukleme basarili.");
        } catch (ESYAException e) {
            throw new GelecexSignerException("Tubitak Lisans dosyasi yukleme hatasi!" , e);
        }
    }

}

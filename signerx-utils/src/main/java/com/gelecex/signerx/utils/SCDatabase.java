package com.gelecex.signerx.utils;

import com.gelecex.signerx.common.smartcard.SmartcardAtr;
import com.gelecex.signerx.common.smartcard.SmartcardLibrary;
import com.gelecex.signerx.common.smartcard.SmartcardType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obetron on 24.04.2022
 */
public class SCDatabase extends DefaultHandler {

    private SmartcardType smartcardType;
    private List<SmartcardType> smartcardTypeList;
    private List<SmartcardLibrary> smartcardLibrayList;
    private List<SmartcardAtr> smartcardAtrList;

    public List<SmartcardType> readSmarcardDatabaseXml() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(getClass().getResourceAsStream("/scdatabase.xml"), this);

        return smartcardTypeList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if("smartcard-config".equalsIgnoreCase(qName)) {
            smartcardTypeList = new ArrayList<>();
        }
        if(qName.equalsIgnoreCase("card-type")) {
            smartcardType = new SmartcardType();
            if(attributes != null && attributes.getLength() > 0) {
                smartcardType.setName(attributes.getValue("name"));
            }
            smartcardLibrayList = new ArrayList<>();
            smartcardAtrList = new ArrayList<>();
        }
        if(qName.equalsIgnoreCase("lib")) {
            SmartcardLibrary smartcardLibrary = new SmartcardLibrary();
            if(attributes.getLength() > 0) {
                if(attributes.getValue("name") != null) {
                    smartcardLibrary.setName(attributes.getValue("name"));
                }
                if (attributes.getValue("arch") != null) {
                    smartcardLibrary.setArch(attributes.getValue("arch"));
                }
            }
            smartcardLibrayList.add(smartcardLibrary);
        }
        if(qName.equalsIgnoreCase("atr")) {
            smartcardType.setLibraryList(smartcardLibrayList);
            SmartcardAtr smartcardAtr = new SmartcardAtr();
            if(attributes != null && attributes.getLength() > 0 && attributes.getValue("value") != null) {
                smartcardAtr.setAtr(attributes.getValue("value"));
            }
            smartcardAtrList.add(smartcardAtr);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("card-type".equalsIgnoreCase(qName)) {
            smartcardType.setAtrList(smartcardAtrList);
            smartcardType.setLibraryList(smartcardLibrayList);
            smartcardTypeList.add(smartcardType);
            smartcardType = new SmartcardType();
        }
    }
}

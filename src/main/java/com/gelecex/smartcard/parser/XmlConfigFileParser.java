package com.gelecex.smartcard.parser;

import com.gelecex.smartcard.exception.XMLParserException;
import com.gelecex.smartcard.utils.GelecexI18n;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by obetron on 22.12.2018
 */
public class XmlConfigFileParser {

    private Document getXmlDocument() throws XMLParserException {
        try {
            InputStream scConfigXml = XmlConfigFileParser.class.getResourceAsStream("/smartcard-config.xml");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(scConfigXml);
            document.getDocumentElement().normalize();
            return document;
        } catch (ParserConfigurationException e) {
            throw new XMLParserException(GelecexI18n.getErrorMessage("documentBuilderError"), e);
        } catch (SAXException e) {
            throw new XMLParserException(GelecexI18n.getErrorMessage("documentSAXError"), e);
        } catch (IOException e) {
            throw new XMLParserException(GelecexI18n.getErrorMessage("documentIOError"), e);
        }
    }



}

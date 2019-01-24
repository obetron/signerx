package com.gelecex.smartcard.parser;

import com.gelecex.smartcard.exception.XMLParserException;
import com.gelecex.smartcard.utils.GelecexI18n;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by obetron on 22.12.2018
 */
public class XMLParserImpl implements XMLParser {

    @Override
    public Document getXmlDocument(InputStream configStream) throws XMLParserException {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            Document document = dbBuilder.parse(configStream);
            document.getDocumentElement().normalize();
            return document;
        } catch (ParserConfigurationException e) {
            throw new XMLParserException(GelecexI18n.getErrorMessage("errorCreatingDocumentBuilder"), e);
        } catch (SAXException e) {
            throw new XMLParserException(GelecexI18n.getErrorMessage("errorParsingStreamToDocument"), e);
        } catch (IOException e) {
            throw new XMLParserException(GelecexI18n.getErrorMessage("errorIOStream"), e);
        }
    }

    @Override
    public NodeList getTagFromNode(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        return nodeList;
    }

    @Override
    public String getAttributeFromNode(Node node, String attributeName) {
        String attributeValue = node.getAttributes().getNamedItem(attributeName).getNodeValue();
        return attributeValue;
    }
}

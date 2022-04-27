package com.gelecex.signerx.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by obetron on 28.12.2018
 */
public interface XMLParser {


    Document getXmlDocument(InputStream configStream) throws ParserConfigurationException, IOException, SAXException;
    NodeList getTagFromNode(Element element, String tagName);
    String getAttributeFromNode(Node node, String attributeName);
}

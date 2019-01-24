package com.gelecex.smartcard.parser;

import com.gelecex.smartcard.exception.XMLParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * Created by obetron on 28.12.2018
 */
public interface XMLParser {


    Document getXmlDocument(InputStream configStream) throws XMLParserException;
    NodeList getTagFromNode(Element element, String tagName);
    String getAttributeFromNode(Node node, String attributeName);
}

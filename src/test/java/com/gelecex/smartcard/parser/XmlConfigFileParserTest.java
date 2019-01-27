package com.gelecex.smartcard.parser;

import com.gelecex.smartcard.exception.XMLParserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * Created by obetron on 23.12.2018
 */
public class XmlConfigFileParserTest {

    private InputStream configFile;
    private XMLParser xmlParser;

    @Before
    public void init() {
        configFile = XmlConfigFileParserTest.class.getResourceAsStream("/smartcard-config.xml");
        xmlParser = new XMLParserImpl();
    }

    private Document getDocument() throws XMLParserException {
        Document doc = xmlParser.getXmlDocument(configFile);
        return doc;
    }

    @Test
    public void testGetXmlDocument() throws XMLParserException {
        Document doc = getDocument();
        Assert.assertNotNull(doc);
    }

    @Test
    public void testGetTagFromNode() throws XMLParserException {
        Document doc = getDocument();
        Element element = doc.getDocumentElement();
        NodeList nodeList = xmlParser.getTagFromNode(element, "card-type");
        Assert.assertEquals(5, nodeList.getLength());
    }

    @Test
    public void testGetAttributeFromNode() throws XMLParserException {
        Document doc = getDocument();
        Element element = doc.getDocumentElement();
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Node cardTypeNode = cardTypeNodeList.item(0);
        String cardTypeName = xmlParser.getAttributeFromNode(cardTypeNode, "name");
        Assert.assertEquals("CARDOS", cardTypeName);
    }

    @Test
    public void testGetSubTagFromNode() throws XMLParserException {
        Document doc = getDocument();
        Element element = doc.getDocumentElement();
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element el = (Element) cardTypeNodeList.item(4);
        NodeList libNodeList = xmlParser.getTagFromNode(el, "lib");
        Assert.assertEquals(2, libNodeList.getLength());
    }

    @Test
    public void testGetSubAttributeFromNode() throws XMLParserException {
        Document doc = getDocument();
        Element element = doc.getDocumentElement();
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element subElement = (Element) cardTypeNodeList.item(0);
        NodeList libNodeList = xmlParser.getTagFromNode(subElement, "lib");
        Node libNode = libNodeList.item(0);
        String libName = xmlParser.getAttributeFromNode(libNode, "name");
        Assert.assertEquals("cmp11", libName);
    }
}

package com.gelecex.signerx.parser;

import com.gelecex.signerx.exception.SignerxXMLParserException;
import com.gelecex.signerx.parser.XMLParserImpl;

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
    private Document doc;
    private Element element;

    @Before
    public void init() throws SignerxXMLParserException {
        configFile = XmlConfigFileParserTest.class.getResourceAsStream("/smartcard-config.xml");
        xmlParser = new XMLParserImpl();
        doc = xmlParser.getXmlDocument(configFile);
        element = doc.getDocumentElement();
    }

    @Test
    public void testGetXmlDocument() {
        Assert.assertNotNull(doc);
    }

    @Test
    public void testGetTagFromNode() {
        NodeList nodeList = xmlParser.getTagFromNode(element, "card-type");
        Assert.assertEquals(6, nodeList.getLength());
    }

    @Test
    public void testGetAttributeFromNode() {
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Node cardTypeNode = cardTypeNodeList.item(0);
        String cardTypeName = xmlParser.getAttributeFromNode(cardTypeNode, "name");
        Assert.assertEquals("CARDOS", cardTypeName);
    }

    @Test
    public void testGetSubTagFromNode() {
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element el = (Element) cardTypeNodeList.item(4);
        NodeList libNodeList = xmlParser.getTagFromNode(el, "lib");
        Assert.assertEquals(2, libNodeList.getLength());
    }

    @Test
    public void testGetSubAttributeFromNode() {
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element subElement = (Element) cardTypeNodeList.item(0);
        NodeList libNodeList = xmlParser.getTagFromNode(subElement, "lib");
        Node libNode = libNodeList.item(0);
        String libName = xmlParser.getAttributeFromNode(libNode, "name");
        Assert.assertEquals("cmp11", libName);
    }

    @Test
    public void testGetSubAtrTagFromNode() {
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element el = (Element) cardTypeNodeList.item(3);
        NodeList atrNodeList = xmlParser.getTagFromNode(el, "atr");
        Assert.assertEquals(4, atrNodeList.getLength());
    }

    @Test
    public void testGetSubAtrAttributesFromNode() {
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element subElement = (Element) cardTypeNodeList.item(2);
        NodeList atrNodeList = xmlParser.getTagFromNode(subElement, "atr");
        Node atrNode = atrNodeList.item(0);
        String atrValue = xmlParser.getAttributeFromNode(atrNode, "value");
        Assert.assertEquals("3BD5180081313A7D8073C8211030", atrValue);
    }

    @Test
    public void testGetAttributesNullValue() {
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        Element cardTypeElement = (Element) cardTypeNodeList.item(0);
        NodeList atrNodeList = xmlParser.getTagFromNode(cardTypeElement, "atr");
        Node atrNode = atrNodeList.item(0);
        Assert.assertEquals(null, atrNode);
    }
}

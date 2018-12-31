package com.gelecex.smartcard.parser;

import com.gelecex.smartcard.exception.XMLParserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.List;

/**
 * Created by obetron on 23.12.2018
 */
public class XmlConfigFileParserTest {

    private InputStream configFile;
    private XMLParser xmlParser;

    @Before
    public void init() {
        configFile = XmlConfigFileParserTest.class.getResourceAsStream("/smartcard-config.xml");
        xmlParser = new FileXMLParserImpl();
    }

    private Document getDocument() throws XMLParserException {
        Document document = xmlParser.getXmlDocument(configFile);
        return document;
    }

    @Test
    public void getXmlDocumentTest() throws XMLParserException {
       Document document = getDocument();
       Assert.assertNotNull(document);
    }

    private NodeList getNodeList(String tagName) throws XMLParserException {
        NodeList nodeList =  xmlParser.getTagFromNode(getDocument().getDocumentElement(), tagName);
        return nodeList;
    }

    @Test
    public void getTagFromNodeTest() throws XMLParserException {
        NodeList cardTypeNodeList = getNodeList("card-type");
        Assert.assertNotNull(cardTypeNodeList);
    }

    private List<Node> getnode(String tagName, String attributeName) throws XMLParserException {
        NodeList nodeList =  getNodeList(tagName);
        List<Node> nodes = xmlParser.getAttributeFromNode(nodeList, attributeName);
        return nodes;
    }

    @Test
    public void getAttributeFromNodeTest() throws XMLParserException {
        List<Node> nodeList = getnode("card-type", "name");
        Assert.assertEquals(nodeList.size(), 3);
        Assert.assertEquals(nodeList.get(0).getNodeValue(), "CARDOS");
    }

    @Test
    public void getATRFromNodeTest() throws XMLParserException {
       NodeList atrNodeList = getNodeList("atr");
       Assert.assertNotNull(atrNodeList);
    }

    @Test
    public void getATRValueFromNodeTest() throws XMLParserException {
        List<Node> atrList = xmlParser.getAttributeFromNode(getNodeList("atr"), "value");
        Assert.assertEquals(atrList.size(), 2);
        Assert.assertEquals(atrList.get(0).getNodeValue(), "3BD5180081313A7D8073C8211030");
    }

    @Test
    public void getLibFromNodeList() throws XMLParserException {
        NodeList libNodeList = getNodeList("lib");
        Assert.assertNotNull(libNodeList);
    }

    @Test
    public void getLibNameFromNodeList() throws XMLParserException {
        List<Node> libList = xmlParser.getAttributeFromNode(getNodeList("lib"), "name");
        Assert.assertEquals(libList.size(), 3);
        Assert.assertEquals(libList.get(0).getNodeValue(), "cmp11");
    }

}

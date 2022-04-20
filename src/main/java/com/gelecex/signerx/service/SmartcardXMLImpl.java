package com.gelecex.signerx.service;

import com.gelecex.signerx.exception.SignerxXMLParserException;
import com.gelecex.signerx.model.SmartcardLibrary;
import com.gelecex.signerx.parser.XMLParserImpl;
import com.gelecex.signerx.model.Smartcard;
import com.gelecex.signerx.parser.XMLParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class SmartcardXMLImpl implements SmartcardDao {

    private XMLParser xmlParser;
    private List<Smartcard> smartcardList;

    @Override
    public void saveSmartcard(Smartcard smartcard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateSmartcard(Smartcard smartcard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteSmartcard(Smartcard smartcard) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Smartcard getSmartcard(String atrValue) throws SignerxXMLParserException {
        Document doc = getDocumentFromProperties();
        Element element = doc.getDocumentElement();
        NodeList nodeList = xmlParser.getTagFromNode(element, "card-type");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Smartcard smartcard = getSmartcard(nodeList.item(i));
            List<String> atrValueList = smartcard.getAtrValueList();
            for (String tempAtrValue : atrValueList) {
                if(tempAtrValue.equals(atrValue)){
                    return smartcard;
                }
            }
        }
        return null;
    }

    @Override
    public List<Smartcard> getSmartcardList() throws SignerxXMLParserException {
        Document doc = getDocumentFromProperties();
        Element element = doc.getDocumentElement();
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        smartcardList = new ArrayList<>();
        for (int i = 0; i < cardTypeNodeList.getLength(); i++) {
            Smartcard smartcard = getSmartcard(cardTypeNodeList.item(i));
            smartcardList.add(smartcard);
        }
        return smartcardList;
    }

    private Document getDocumentFromProperties() throws SignerxXMLParserException {
        xmlParser = new XMLParserImpl();
        InputStream smartcardConfigStream = SmartcardXMLImpl.class.getResourceAsStream("/smartcard-config.xml");
        Document doc = xmlParser.getXmlDocument(smartcardConfigStream);
        return doc;
    }

    private Smartcard getSmartcard(Node cardTypeNode) {
        Smartcard smartcard = new Smartcard();
        String cardTypeName = xmlParser.getAttributeFromNode(cardTypeNode, "name");
        smartcard.setCardType(cardTypeName);
        smartcard.setSmartcardLibraryList(getSmartcardLibraryList((Element) cardTypeNode));
        smartcard.setAtrValueList(getAtrValueList((Element) cardTypeNode));
        return smartcard;
    }

    private List<SmartcardLibrary> getSmartcardLibraryList(Element cardTypeNodeElement) {
        NodeList libNodeList = xmlParser.getTagFromNode(cardTypeNodeElement, "lib");
        List<SmartcardLibrary> smartcardLibraryList = new ArrayList<>();
        for (int j = 0; j < libNodeList.getLength(); j++) {
            SmartcardLibrary smartcardLibrary = new SmartcardLibrary();
            Node libNode = libNodeList.item(j);
            String libName = xmlParser.getAttributeFromNode(libNode, "name");
            String archName = xmlParser.getAttributeFromNode(libNode, "arch");
            smartcardLibrary.setName(libName);
            smartcardLibrary.setArch(archName);
            smartcardLibraryList.add(smartcardLibrary);
        }
        return smartcardLibraryList;
    }

    private List<String> getAtrValueList(Element cardTypeNodeElement) {
        NodeList atrNodeList = xmlParser.getTagFromNode(cardTypeNodeElement, "atr");
        List<String> atrValueList = new ArrayList<>();
        for (int j = 0; j < atrNodeList.getLength(); j++) {
            Node atrNode = atrNodeList.item(j);
            String atrValue = xmlParser.getAttributeFromNode(atrNode, "value");
            atrValueList.add(atrValue);
        }
        return atrValueList;
    }
}

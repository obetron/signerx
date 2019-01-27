package com.gelecex.smartcard.model;

import com.gelecex.smartcard.exception.XMLParserException;
import com.gelecex.smartcard.parser.XMLParser;
import com.gelecex.smartcard.parser.XMLParserImpl;
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

    }

    @Override
    public void updateSmartcard(Smartcard smartcard) {

    }

    @Override
    public void deleteSmartcard(Smartcard smartcard) {

    }

    @Override
    public Smartcard getSmartcard(String lib) throws XMLParserException {
        return null;
    }

    @Override
    public List<Smartcard> getSmartcardList() throws XMLParserException {
        Document doc = getDocumentFromProperties();
        Element element = doc.getDocumentElement();
        NodeList cardTypeNodeList = xmlParser.getTagFromNode(element, "card-type");
        smartcardList = new ArrayList<>();
        for (int i = 0; i < cardTypeNodeList.getLength(); i++) {
            Smartcard smartcard = new Smartcard();
            Node cardTypeNode = cardTypeNodeList.item(i);
            String cardTypeName = xmlParser.getAttributeFromNode(cardTypeNode, "name");
            smartcard.setCardType(cardTypeName);

            Element cardTypeElement = (Element) cardTypeNodeList.item(i);
            NodeList libNodeList = xmlParser.getTagFromNode(cardTypeElement, "lib");
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
            smartcard.setSmartcardLibraryList(smartcardLibraryList);

            NodeList atrNodeList = xmlParser.getTagFromNode(cardTypeElement, "atr");
            List<String> atrValueList = new ArrayList<>();
            for (int j = 0; j < atrNodeList.getLength(); j++) {
                Node atrNode = atrNodeList.item(j);
                String atrValue = xmlParser.getAttributeFromNode(atrNode, "value");
                atrValueList.add(atrValue);
            }
            smartcard.setAtrValueList(atrValueList);
            smartcardList.add(smartcard);
        }
        return smartcardList;
    }

    private Document getDocumentFromProperties() throws XMLParserException {
        xmlParser = new XMLParserImpl();
        InputStream smartcardConfigStream = SmartcardXMLImpl.class.getResourceAsStream("/smartcard-config.xml");
        Document doc = xmlParser.getXmlDocument(smartcardConfigStream);
        return doc;
    }
}

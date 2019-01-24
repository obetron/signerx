package com.gelecex.smartcard.model;

import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class SmartcardDAOTest {


    private SmartcardDao smartcardDAO;
    List<Smartcard> smartcardList;

//    @Before
//    public void initTest() throws SmartcardReaderException {
//        smartcardDAO = new SmartcardXMLImpl();
//        smartcardList = smartcardDAO.getSmartcardList();
//    }
//
//    @Test
//    public void saveSmartcard() throws SmartcardReaderException {
//        int currentSmartcardListSize = smartcardList.size();
//        Smartcard newSmartcard = new Smartcard("ALADDIN", "eTPKCS11", "3BD5180081313A7D8073C8211030");
//        smartcardDAO.saveSmartcard(newSmartcard);
//
//        smartcardList = smartcardDAO.getSmartcardList();
//        Assert.assertEquals("Smartcard List Size Control", (currentSmartcardListSize+1), smartcardList.size());
//    }
//
//    @Test
//    public void getSmartcardList() throws SmartcardReaderException {
//        Smartcard newSmartcard = new Smartcard("ALADDIN", "eTPKCS11", "3BD5180081313A7D8073C8211030");
//        smartcardDAO.saveSmartcard(newSmartcard);
//
//        smartcardList = smartcardDAO.getSmartcardList();
//        Assert.assertTrue(smartcardList.size() > 0);
//    }

}

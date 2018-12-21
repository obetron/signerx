package com.gelecex.smartcard.model;

import com.gelecex.smartcard.manager.EntityManager;
import com.gelecex.smartcard.manager.XMLEntityManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by obetron on 19.12.2018
 */
public class SmartcardDAOTest {


    private SmartcardDAO smartcardDAO;
    private EntityManager entityManager;
    List<Smartcard> smartcardList = new ArrayList<>();

    @Before
    public void initTest(){
        entityManager = new XMLEntityManager();
        smartcardDAO = new SmartcardDAOImpl();

        smartcardList = entityManager.getAllSc();
    }

    @Test
    public void saveSmartcard() {
        int currentSmartcardListSize = smartcardList.size();
        Smartcard newSmartcard = new Smartcard("ALADDIN", "eTPKCS11", "3BD5180081313A7D8073C8211030");
        smartcardDAO.saveSmartcard(newSmartcard);

        Assert.assertEquals("Smartcard List Size Control", (currentSmartcardListSize+1), smartcardList.size());
    }

}

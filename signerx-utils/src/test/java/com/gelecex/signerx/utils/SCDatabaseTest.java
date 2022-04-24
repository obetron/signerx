package com.gelecex.signerx.utils;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by obetron on 24.04.2022
 */
public class SCDatabaseTest {

    private SCDatabase scDatabase;

    @Before
    public void init() {
        scDatabase = new SCDatabase();
    }

    @Test
    public void testReadXmlFile() {
        scDatabase.readXmlFile();
    }


}

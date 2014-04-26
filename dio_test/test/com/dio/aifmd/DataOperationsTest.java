package com.dio.aifmd;

import org.junit.Test;
import org.junit.Before;

/**
 * Created by iovchynnikov on 4/26/14.
 */
public class DataOperationsTest {
    private DataConnect2 dataJdbc, dataJdbcDriver, dataTds;

    @Before
    public void setUp() {
        dataJdbc = new DataConnect2(new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), "SQL Anywhere 12", "jdbc:sqlanywhere:");
        dataJdbcDriver = new DataConnect2(new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), "SQL Anywhere 12", "jdbc:sqlanywhere:");
        dataTds = new DataConnect2(new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), null, "jdbc:sybase:Tds:");
    }

    @Test
    public void testPerformSelect() throws Exception {


    }
}

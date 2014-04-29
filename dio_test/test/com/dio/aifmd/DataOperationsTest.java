package com.dio.aifmd;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by iovchynnikov on 4/26/14.
 */
public class DataOperationsTest {
    private DataConnect2 dataJdbc, dataJConnect;
    private DataOperations operations;
    private String[] selectResult;
    private String selectQuery, loadQuery, loadTable;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        dataJdbc = new DataConnect2(new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), "SQL Anywhere 12", "jdbc:sqlanywhere:");
        dataJConnect = new DataConnect2(new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), null, "jdbc:sybase:Tds:");
        selectQuery = "select '1' as f_1, 2 as f_2, '3' as f_3, 4 as f_4";
        selectResult =  new String[]{"1", "2", "3", "4"};
        loadTable = "t_Fund_Tax_Lot_Holdings";

        String path = this.getClass().getResource("/formpf_Holdings_iGLS_PAULSON_20130630_20131021 1955.csv").getPath();
        File file = new File(path);
        // required full access

        path = file.getAbsolutePath().replace("%20", " ").replace("\\", "\\\\");

        loadQuery = "load table " + loadTable + " (\n" +
                "\tf_Report_Date       ,\n" +
                "\tf_Master_Adviser_ID  ,\n" +
                "\tf_Master_Adviser_Desc ,\n" +
                "\tf_Master_Fund_ID     ,\n" +
                "\tf_Master_Fund_Desc   ,\n" +
                "\tf_Source_Adviser_ID  ,\n" +
                "\tf_Source_Adviser_Desc ,\n" +
                "\tf_Source_Fund_ID     ,\n" +
                "\tf_Source_Fund_Desc   ,\n" +
                "\tf_Security_ID        ,\n" +
                "\tf_Cusip              ,\n" +
                "\tf_ISIN               ,\n" +
                "\tf_SEDOL              ,\n" +
                "\tf_Security_Desc      ,\n" +
                "\tf_Underlier_Security_ID ,\n" +
                "\tf_Underlier_Security_Desc ,\n" +
                "\tf_Strategy           ,\n" +
                "\tf_Lot_ID             ,\n" +
                "\tf_Lot_Trade_Date     ,\n" +
                "\tf_Lot_Settlement_Date ,\n" +
                "\tf_Long_Short         ,\n" +
                "\tf_Position           ,\n" +
                "\tf_Asset_Type         ,\n" +
                "\tf_Sub_Asset_Type     ,\n" +
                "\tf_FASB_157           ,\n" +
                "\tf_Country_Code       ,\n" +
                "\tf_Counterparty_ID    ,\n" +
                "\tf_Counterparty_Desc  ,\n" +
                "\tf_Custodian_Code     ,\n" +
                "\tf_Custodian_Name     ,\n" +
                "\tf_Clearing_Broker_Code ,\n" +
                "\tf_Exchange           ,\n" +
                "\tf_Base_Market_Value  ,\n" +
                "\tf_Base_Gross_Exposure ,\n" +
                "\tf_GAV                ,\n" +
                "\tf_NAV                ,\n" +
                "\tf_Accrued_Income     ,\n" +
                "\tf_Maturity_Date      ,\n" +
                "\tf_Base_Cost          ,\n" +
                "\tf_Asset_Liability_BMV ,\n" +
                "\tf_Asset_Liability_BMVAI ,\n" +
                "\tf_Base_Dirty_Price_Amount ,\n" +
                "    f_Base_Collateral_Market_Value_Amount \n" +
                ") \n" +
                "USING CLIENT FILE \n" +
                "'" + path + "'\n" +
                "ESCAPES OFF\n" +
//                "ON FILE ERROR CONTINUE\n" +
                "MESSAGE LOG 'c:\\temp\\test.log' ROW LOG 'c:\\temp\\test.row' ONLY LOG ALL";
    }

    @Test
    public void testConnectJdbc() throws Exception {
        if (dataJdbc.connect())
            dataJdbc.disconnect();
    }

    @Test
    public void testConnectJConnect() throws Exception {
        if (dataJConnect.connect())
            dataJConnect.disconnect();
    }

    @Test
    public void testPerformQueryJdbc() throws Exception {
        dataJdbc.connect();
        operations = new DataOperations(dataJdbc);
        assertArrayEquals("Test custom query JDbc", selectResult, operations.performQuery(selectQuery));
        dataJdbc.disconnect();
    }

    @Test
    public void testPerformQueryJConnect() throws Exception {
        dataJConnect.connect();
        operations = new DataOperations(dataJConnect);
        assertArrayEquals("Test custom query jConnect", selectResult, operations.performQuery(selectQuery));
        dataJConnect.disconnect();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //@Test
    public void testPerformLoadJconnect() throws Exception {
        thrown.expect(java.sql.BatchUpdateException.class);
        dataJConnect.connect();
        operations = new DataOperations(dataJConnect);
        assertTrue("Test load JConnect", operations.performLoad(loadQuery));
        dataJConnect.disconnect();
    }

    @Test
    public void testPerformLoadJdbc() throws Exception {
        dataJdbc.connect();
        operations = new DataOperations(dataJdbc);
        operations.performDelete(loadTable);
        assertTrue("Test load JDbc", operations.performLoad(loadQuery));
        dataJdbc.disconnect();
    }

    @Test
    public void testPerformQueryMock() throws SQLException {
        Connection testConn = mock(Connection.class);
        Statement testStatement = mock(Statement.class);

        dataJdbc = mock(DataConnect2.class);

        when(dataJdbc.getConn()).thenReturn(testConn);
        when(testConn.createStatement()).thenReturn(testStatement);

//                (new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), "SQL Anywhere 12", "jdbc:sqlanywhere:");
        operations = new DataOperations(dataJdbc);



    }

}

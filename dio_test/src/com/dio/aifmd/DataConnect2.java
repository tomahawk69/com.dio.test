package com.dio.aifmd;

import com.sybase.jdbc4.jdbc.SybDriver;

import java.sql.*;
import java.util.Arrays;

//import org.;
// TODO: add mybatis

/**
 * Created by iovchynnikov on 4/23/14.
 * To control who can use client-side bulk loads, use the secure feature (-sf) server startup switch, the ALLOW_READ_CLIENT_FILE database option, and/or the READCLIENTFILE access control.
 * set option public.allow_read_client_file = ON
 */
public class DataConnect2 {
    private Connection conn;
    private String driver, url, username, password;
    private ConnectionInfo connectionInfo;


    public DataConnect2(ConnectionInfo connectionInfo, String driver, String url) {
        this.connectionInfo = connectionInfo;
        this.driver = driver;
        this.url = url;
    }

    public Boolean connect() throws ClassNotFoundException, SQLException {
        // assume driver absent is default for Tds
        if (driver == null) {
            Class.forName("ianywhere.ml.jdbcodbc.jdbc4.IDriver");
        }
        conn = DriverManager.getConnection(getConnectionUrl(), connectionInfo.getUser(), connectionInfo.getPwd());
        return true;
    }

    private String getConnectionUrl() {
        // jdbc:sybase:Tds:
        // jdbc:sqlanywhere:
        String result = url;

        if (driver == null)
            // Tds
            result += connectionInfo.getServerUrlTds();
        else
            // Jdbc
            result += connectionInfo.getServerUrlJdbc();
        return result;
    }

    public boolean checkQuery() {
        boolean result = false;
        try {
            Statement sql = conn.createStatement();
            System.out.print("Querying...");
            ResultSet query = sql.executeQuery("select * from t_Currency_Type");
            //ResultSet query = sql.executeQuery("SELECT GETDATE()");
            //ResultSet query = sql.executeQuery("SELECT GETDATE()");

            System.out.println("done");
            while (query.next()) {
                if (query.getString(1).equals("0")) {
                    System.out.println("Item exists");
                    result = true;
                }
            };
            query.close();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkInsert() {
        try {
            Statement sql = conn.createStatement();
            System.out.print("Inserting...");
            sql.addBatch("insert into t_Currency_Type values ('0', '~~~')");
            int[] rowsCount = sql.executeBatch();
            System.out.println(Arrays.toString(rowsCount));
            conn.rollback();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean checkDelete() {
        boolean result = false;
        try {
            Statement sql = conn.createStatement();
            System.out.print("Deleting...");
            Integer rowsCount = sql.executeUpdate("delete from t_Fund_Tax_Lot_Holdings");
            System.out.println("done");
            result = rowsCount > 0;
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkLoad() {
        try {
            Statement sql = conn.createStatement();
            System.out.print("Loading...");
            sql.addBatch("load table t_Fund_Tax_Lot_Holdings (\n" +
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
                    "'C:\\\\Temp\\\\formpf_Holdings_iGLS_PAULSON_20130630_20131021 1955.csv'\n" +
                    "ESCAPES OFF"
            );
            int[] rowsCount = sql.executeBatch();
            System.out.println(Arrays.toString(rowsCount));
            conn.rollback();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void disconnect() {
        System.out.print("Close connection...");
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        System.out.println("done");
    }

}

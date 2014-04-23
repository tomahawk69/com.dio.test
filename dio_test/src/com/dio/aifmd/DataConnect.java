package com.dio.aifmd;

import com.dio.test.PojoPerson;
import com.sybase.jdbc4.jdbc.SybDriver;
//import org.;
// TODO: add mybatis
import java.sql.*;
import java.util.Arrays;

/**
 * Created by iovchynnikov on 4/23/14.
 */
public class DataConnect {
    private static SybDriver sybDriver;
    private static Connection conn;
    private static String url, username, password;

    static {
        //sybDriver = SybDriver();
        try {
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Driver loaded");
        url = String.format("jdbc:sybase:Tds:%s:%s/%s?ENABLE_BULK_LOAD=true", "DIO32", "2638", "idas_dev");
        username = "AIFMD_DEV";
        password = "AIFMD_DEV1234!";

    }

    public static boolean init() {
        System.out.print("Connecting...");
        try {
            conn = DriverManager.getConnection(url, username, password);
            //conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
            return false;
        }
        System.out.println("done");
        return true;
    }

    public static boolean checkQuery() {
        boolean result = false;
        try {
            Statement sql = conn.createStatement();
            System.out.print("Querying...");
            ResultSet query = sql.executeQuery("select * from t_Currency_Type");
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

    private static boolean checkInsert() {
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

    private static boolean checkDelete() {
        boolean result = false;
        try {
            Statement sql = conn.createStatement();
            System.out.print("Deleting...");
            Integer rowsCount = sql.executeUpdate("delete from t_Currency_Type where f_currency_type_id = 0");
            System.out.println("done");
            result = rowsCount > 0;
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        return result;
    }

    private static void release() {
        System.out.print("Close connection...");
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
        System.out.println("done");
    }

    public static void main(String[] args) {
        if (!init())
            System.exit(0);
        if (!checkQuery() || checkDelete())
            checkInsert();
        release();
    }
}

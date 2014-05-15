package com.dio.aifmd;

import org.relique.jdbc.csv.CsvDriver;

import java.sql.*;
import java.util.Properties;

/**
 * Created by iovchynnikov on 5/12/14.
 */
public class DataConnectCSV {
    public static void main(String[] args)
    {
        try
        {
            // Load the driver.
            Class.forName("com.caigen.sql.text.CSVDriver");
            Properties props = new Properties();
            props.setProperty("delayedClose", "0");


            // Create a connection. The first command line parameter is
            // the directory containing the .csv files.
            // A single connection is thread-safe for use by several threads.
            String csvFileName = "D:\\Java\\Work\\com.dio.test\\dio_test\\Resource\\csv\\";
            Connection conn = DriverManager.getConnection("jdbc:csv:/d:/java/?_CSV_Header=false", props);

            // Create a Statement object to execute the query with.
            // A Statement is not thread-safe.
            Statement stmt = conn.createStatement();

            String sql = "drop table if exists testUsers;";
            System.out.println(sql);
            stmt.execute(sql);

            sql = "create table if not exists testUsers  (FirstName varchar(25), LastName varchar(25), LastDate date, intNum integer, floatNum numeric(12,2));" +
                    "";
            System.out.println(sql);
            stmt.execute(sql);

            sql = "insert into testUsers values('John','Pull', '2013-01-26', 10, 0.12);";
            System.out.println(sql);
            stmt.execute(sql);

            sql = "insert into testUsers values('John Dow','Pull, me', null, null, null);";
            System.out.println(sql);
            stmt.execute(sql);
            stmt.close();


            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

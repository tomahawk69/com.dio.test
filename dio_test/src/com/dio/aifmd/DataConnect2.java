package com.dio.aifmd;

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
            Class.forName("com.sybase.jdbc4.jdbc.SybDriver");
        }
        conn = DriverManager.getConnection(getConnectionUrl(), connectionInfo.getUser(), connectionInfo.getPwd());
        return true;
    }


    public Connection getConn() {
        return conn;
    }

    private String getConnectionUrl() {
        // jdbc:sybase:Tds:
        // jdbc:sqlanywhere:
        String result = url;

        if (driver == null)
            // JConnect
            result += connectionInfo.getServerUrlJConnect();
        else
            // Jdbc
            result += connectionInfo.getServerUrlJdbc();
        System.out.println(result);
        return result;
    }

    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

}

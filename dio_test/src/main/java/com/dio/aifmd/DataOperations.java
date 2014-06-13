package com.dio.aifmd;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by iovchynnikov on 4/26/14.
 */
public class DataOperations {
    private DataConnect2 data;
    private String sqlSelect;



    public DataOperations(DataConnect2 data) {
        this.data = data;
        sqlSelect = "select '1' as f_1, '2' as f_2";
    }

    /**
     * Execute any query, return -=first=- row with casted to string values from result if exists
     * @param query
     * @return array of string values from first row
     */
    public String[] performQuery(String query) throws SQLException, ClassNotFoundException {
        if (data.getConn() == null)
            data.connect();
        Statement sql = data.getConn().createStatement();
        ResultSet results = sql.executeQuery(query);
        ResultSetMetaData metadata = results.getMetaData();
        String[] result;
        // if results exists
        if (results.next()) {
            result = new String[metadata.getColumnCount()];
            for (int i = 1; i <= result.length; i++)
                result[i-1] = results.getString(i);
        }
        else
            result = new String[0];
        results.close();
        sql.close();
        return result;
    }

    public String[] performQueryParams(String query, String... params) throws SQLException, ClassNotFoundException {
        if (data.getConn() == null)
            data.connect();
        PreparedStatement sql = data.getConn().prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            sql.setString(i+1, params[i-1]);
        }
        ResultSet results = sql.executeQuery();
        ResultSetMetaData metadata = results.getMetaData();
        String[] result;
        // if results exists
        if (results.next()) {
            result = new String[metadata.getColumnCount()];
            for (int i = 1; i <= result.length; i++)
                result[i-1] = results.getString(i);
        }
        else
            result = new String[0];
        results.close();
        sql.close();
        return result;
    }

    /**
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public boolean performLoad(String query) throws SQLException {
        Statement sql = data.getConn().createStatement();
        sql.execute(query);
        boolean result = (sql.getUpdateCount() > 0);
        sql.close();
        return result;
    }

    public boolean performDelete(String table) throws SQLException {
        Statement sql = data.getConn().createStatement();
        sql.execute("delete from " + table);
        boolean result = (sql.getUpdateCount() > 0);
        sql.close();
        return result;
    }

    /**
     * Select
     * @param table
     * @param fields
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String[] performSelect(String table, String[] fields) throws SQLException, ClassNotFoundException {
        Statement sql = data.getConn().createStatement();
        ResultSet query = sql.executeQuery(sqlSelect);
        //if (query.next())
        return new String[0];
    }

    public Integer performInsertParams(String query, String... params) throws SQLException, ClassNotFoundException {
        if (data.getConn() == null)
            data.connect();
        PreparedStatement sql = data.getConn().prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            sql.setString(i, params[i - 1]);
        }
        //sql.setString(1, "test");
        int result = sql.executeUpdate();
        return result;
    }

    public String[] performSelectParams(String query, String... params) throws SQLException, ClassNotFoundException {
        if (data.getConn() == null)
            data.connect();
        PreparedStatement sql = data.getConn().prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            sql.setString(i, params[i-1]);
        }
        //sql.setString(1, "test");
        ResultSet results = sql.executeQuery();
        ResultSetMetaData metadata = results.getMetaData();
        String[] result;
        // if results exists
        if (results.next()) {
            result = new String[metadata.getColumnCount()];
            for (int i = 1; i <= result.length; i++)
                result[i-1] = results.getString(i);
        }
        else
            result = new String[0];
        results.close();
        sql.close();
        return result;
    }
}

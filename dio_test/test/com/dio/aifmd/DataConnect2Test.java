package com.dio.aifmd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.both;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.Mockito.*;

/**
 * Created by iovchynnikov on 4/26/14.
 */
public class DataConnect2Test {
    private DataConnect2 dataJdbc, dataJConnect;
    private ConnectionInfo connectionInfo;
    private String user, pwd, server, ip, databaseName;
    private Integer port;

    @Before
    public void setUp() {
        connectionInfo = mock(ConnectionInfo.class);
        dataJdbc = new DataConnect2(connectionInfo, "SQL Anywhere 12", "jdbc:sqlanywhere:");
        dataJConnect = new DataConnect2(connectionInfo, null, "jdbc:sybase:Tds:");
        user = "AIFMD_DEV";
        pwd = "AIFMD_DEV1234!";
        server = "idas_dev";
        ip = "dio32";
        databaseName = "idas_dev";
        port = 2638;
    }

    @Test
    public void testConnectJdbc() throws Exception {

        when(connectionInfo.getUser()).thenReturn(user);
        when(connectionInfo.getPwd()).thenReturn(pwd);
        when(connectionInfo.getServerUrlJdbc()).thenReturn(String.format("Server=%s;", server) +
                String.format("DBN=%s;", databaseName) +
                String.format("links=tcpip{ip=%s;port=%d};", ip, port));

        assertTrue("DataConnect2 Jdbc test", dataJdbc.connect());

        verify(connectionInfo).getServerUrlJdbc();
        verify(connectionInfo).getUser();
        verify(connectionInfo).getPwd();
    }

    @Test
    public void testConnectJConnect() throws Exception {

        when(connectionInfo.getUser()).thenReturn(user);
        when(connectionInfo.getPwd()).thenReturn(pwd);
        when(connectionInfo.getServerUrlJConnect()).thenReturn(String.format("%s:%d/%s?ENABLE_BULK_LOAD=true", ip, port, databaseName));

        assertTrue("DataConnect2 JConnect test", dataJConnect.connect());

        verify(connectionInfo).getServerUrlJConnect();
        verify(connectionInfo).getUser();
        verify(connectionInfo).getPwd();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testConnectJConnectWronged() throws Exception {
        thrown.expect(java.sql.SQLException.class);
        thrown.expectMessage(both(containsString("UnknownHostException")).and(containsString(server)));

        when(connectionInfo.getUser()).thenReturn(user);
        when(connectionInfo.getPwd()).thenReturn(pwd);
        when(connectionInfo.getServerUrlJConnect()).thenReturn(String.format("%s:%d/%s?ENABLE_BULK_LOAD=true", server, port, databaseName));

        assertTrue("DataConnect2 JConnect wrong test", dataJConnect.connect());
    }

}

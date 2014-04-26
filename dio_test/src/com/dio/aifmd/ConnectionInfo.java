package com.dio.aifmd;

/**
 * Connection info to pass to DataConnect
 * Created by iovchynnikov on 4/26/14.
 */
public class ConnectionInfo {
    private String user, pwd, server, ip, databaseName;
    private Integer port;

    public ConnectionInfo(String server, String ip, Integer port, String databaseName, String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
        this.server = server;
        this.databaseName = databaseName;
        this.port = port;
        this.ip = ip;
    }

    public String getServerUrlTds () {
        return String.format("%s:%d/%s?ENABLE_BULK_LOAD=true", server, port, databaseName);
    }

    public String getServerUrlJdbc () {
        return (server != null ? String.format("Server=%s;", server) : "") +
                (databaseName != null ? String.format("DBN=%s;", databaseName) : "") +
                (ip != null && port != null ? String.format("links=tcpip{ip=%s;port=%d};", ip, port) : "");
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }
}

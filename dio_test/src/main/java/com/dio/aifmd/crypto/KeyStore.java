package com.dio.aifmd.crypto;

/**
 * Created by yur on 15.06.2014.
 */
public class KeyStore {
    private final FileService fileService;

    public KeyStore(FileService fileService) {
        this.fileService = fileService;
    }

    public String getKey(String keyName) {
        return null;
    }

    public Boolean isKeyExists(String keyName) {
        return false;
    }
}

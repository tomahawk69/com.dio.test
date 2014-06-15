package com.dio.aifmd.crypto;

import java.nio.file.Path;

/**
 * Created by iovchynnikov on 6/13/2014.
 */
public class FileService {

    private static final String PRIVATE_KEY_EXT = ".private";
    private static final String PUBLIC_KEY_EXT = ".public";

    private final Path storePath;

    public FileService(Path storePath) {
        this.storePath = storePath;
    }

    public String constructFileName(String key) {
        return null;
    }

    public Boolean isKeyExists(String keyFileName) {
        return false;
    }

    public String getKeyPrivateName(String keyFileName) {
        return keyFileName + PRIVATE_KEY_EXT;
    }

    public String getKeyPublicName(String keyFileName) {
        return keyFileName + PUBLIC_KEY_EXT;
    }

    public Boolean isPublicKeyExists(String keyFileName) {
        return isKeyExists(getKeyPublicName(keyFileName));
    }

    public Boolean isPrivateKeyExists(String keyFileName) {
        return isKeyExists(getKeyPrivateName(keyFileName));
    }

    public String getKey(String keyFileName) {
        return "";
    }
}

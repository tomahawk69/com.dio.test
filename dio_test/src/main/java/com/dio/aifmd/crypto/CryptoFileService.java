package com.dio.aifmd.crypto;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by iovchynnikov on 6/13/2014.
 */
public class CryptoFileService {

    public static final String PRIVATE_KEY_EXT = ".private";
    public static final String PUBLIC_KEY_EXT = ".public";

    private final Path storePath;

    public CryptoFileService(Path storePath) {
        this.storePath = storePath;
    }

    public Path constructFileName(String key, String ext) {
        return Paths.get(storePath.toString(), key + ext);
    }

    public Path constructPrivateKeyName(String keyFileName) {
        return constructFileName(keyFileName, PRIVATE_KEY_EXT);
    }

    public Path constructPublicKeyName(String keyFileName) {
        return constructFileName(keyFileName, PUBLIC_KEY_EXT);
    }

    public Boolean publicKeyExists(String keyFileName) {
        return fileExists(constructPublicKeyName(keyFileName));
    }

    private Boolean fileExists(Path keyFileName) {
        return Files.exists(keyFileName);
    }

    public Boolean privateKeyExists(String keyFileName) {
        return fileExists(constructPrivateKeyName(keyFileName));
    }

    public String loadFileContent(Path keyFileName) {
        if (fileExists(keyFileName)) {

            return null;
        } else {
            return null;
        }
    }

    public String loadPrivateKey(String keyName) {
        return loadFileContent(constructPrivateKeyName(keyName));
    }

    public String loadPublicKey(String keyName) {
        return loadFileContent(constructPublicKeyName(keyName));
    }
}

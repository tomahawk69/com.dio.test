package com.dio.aifmd.crypto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CryptoFileService {

    public static final String PRIVATE_KEY_EXT = ".private.key";
    public static final String PUBLIC_KEY_EXT = ".public.key";
    public static final String CLASSPATH_DIRECTORY = "keys/";
    private final Path storePath;

    public CryptoFileService(){
        storePath = null;
    }

    public CryptoFileService(Path filePath) {
        this.storePath = filePath;
    }

    public Path constructFileName(String key, String ext) {
        return Paths.get(storePath.toString(), key + ext);
    }

    public String constructFileNameFromCP(String key, String ext){
        StringBuilder builder = new StringBuilder(CLASSPATH_DIRECTORY);
        return builder.append(key).append(ext).toString();
    }

    private Boolean fileExists(Path keyFileName) {
        return Files.exists(keyFileName);
    }

    public Boolean privateKeyExists(String keyFileName) {
        return fileExists(constructFileName(keyFileName, PUBLIC_KEY_EXT));
    }

    public Key loadFileContent(Path keyFileName) {
        Key key = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(keyFileName.toFile()));
            key = (PrivateKey) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }

    public Key loadFileContentFromCP(String keyFileName) {
        Key key = null;
        try {
            ObjectInputStream in = new ObjectInputStream(this.getClass().getClassLoader().getResourceAsStream(keyFileName));
            key = (Key) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return key;
    }

    public PrivateKey loadPrivateKey(String keyName) {
        if (storePath == null) {
            return (PrivateKey) loadFileContentFromCP(constructFileNameFromCP(keyName, PRIVATE_KEY_EXT));
        }
        else{
            return (PrivateKey) loadFileContent(constructFileName(keyName, PRIVATE_KEY_EXT));
        }
    }

    public PublicKey loadPublicKey(String keyName) {
        if (storePath == null) {
            return (PublicKey) loadFileContentFromCP(constructFileNameFromCP(keyName, PUBLIC_KEY_EXT));
        }
        else{
            return (PublicKey) loadFileContent(constructFileName(keyName, PUBLIC_KEY_EXT));
        }
    }


}

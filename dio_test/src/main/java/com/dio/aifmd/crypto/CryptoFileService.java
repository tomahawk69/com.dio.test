package com.dio.aifmd.crypto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
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

    private final Logger logger = LogManager.getLogger(CryptoFileService.class);

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

    public Boolean fileExists(Path keyFileName) {
        return Files.exists(keyFileName);
    }

    public Boolean privateKeyExists(String keyFileName) {
        return fileExists(constructFileName(keyFileName, PRIVATE_KEY_EXT));
    }

    public Boolean publicKeyExists(String keyFileName) {
        return fileExists(constructFileName(keyFileName, PUBLIC_KEY_EXT));
    }

// read file and create a key methods

// common methods
    public ObjectInputStream getObjectInputStream(InputStream in) throws IOException {
        return new ObjectInputStream(in);
    }

    public Key readKeyFromObjectInputStream(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return (Key) inputStream.readObject();
    }

// directory specified methods
    public Key loadFileContent(Path keyFileName) {
        Key key = null;
        try {
            key = readKeyFromFile(keyFileName);
        }
        catch (IOException | ClassNotFoundException e) {
            logger.error(new StringBuilder("Load key from file ").append(keyFileName).append(" error: ").append(e));
            e.printStackTrace();
        }
        return key;
    }

    public Key readKeyFromFile(Path keyFileName) throws IOException, ClassNotFoundException {
        return readKeyFromObjectInputStream(new ObjectInputStream(getFileAsInputStream(keyFileName)));
    }

    public FileInputStream getFileAsInputStream(Path keyFileName) throws FileNotFoundException {
        return new FileInputStream(keyFileName.toFile());
    }

// classpath specified methods
    public Key loadFileContentFromCP(String keyFileName) {
        Key key = null;
        try {
            key = readKeyFromFileCp(keyFileName);
        }
        catch (IOException | ClassNotFoundException e) {
            logger.error(new StringBuilder("Load key from resource ").append(keyFileName).append(" error: ").append(e));
        }
        return key;
    }

    public Key readKeyFromFileCp(String keyFileName) throws IOException, ClassNotFoundException {
        return readKeyFromObjectInputStream(new ObjectInputStream(getResourceAsInputStream(keyFileName)));
    }

    public InputStream getResourceAsInputStream(String keyFileName) {
        return this.getClass().getClassLoader().getResourceAsStream(keyFileName);
    }

// high level load methods

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

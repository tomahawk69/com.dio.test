package com.dio.aifmd.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yur on 15.06.2014.
 */
public class CryptoKeyStore {
    private final CryptoFileService cryptoFileService;

    private Map<String, PrivateKey> privateKeys = new HashMap<>();
    private Map<String, PublicKey> publicKeys = new HashMap<>();

    public CryptoKeyStore(CryptoFileService cryptoFileService) {
        this.cryptoFileService = cryptoFileService;
    }

    public PrivateKey getPrivateKey(String keyName) {
        return privateKeys.get(keyName);
    }

    public PublicKey getPublicKey(String keyName) {
        return publicKeys.get(keyName);
    }

    public Boolean privateKeyExists(String keyName) {
        Boolean result = privateKeys.containsKey(keyName);
        return result;
    }

    public Boolean publicKeyExists(String keyName) {
        Boolean result = publicKeys.containsKey(keyName);
        return result;
    }

    public Boolean loadPrivateKey(String keyName) {
        try {
            //privateKeys.put(keyName, cryptoFileService.loadPrivateKey(keyName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean loadPublicKey(String keyName) {
        try {
            //publicKeys.put(keyName, cryptoFileService.loadPublicKey(keyName));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

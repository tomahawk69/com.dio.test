package com.dio.aifmd.crypto;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        // read from cp public key
        //KeyPair keys = generateKeyFiles();
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(new CryptoFileService(null));
        Boolean uar = cryptoKeyStore.loadPublicKey("uar");

        CryptoLib cryptoLibClient = new CryptoLib(new CryptoKeyStore(new CryptoFileService()));
        byte[] encrStr = cryptoLibClient.encryptString("uar", "uar");
        //String encrStr = cryptoLibClient.encryptStringWithKey(keys.getPublic(), "uar");

        // read from file private key
//        CryptoLib cryptoLibService = new CryptoLib(new CryptoKeyStore(new CryptoFileService(Paths.get("C:\\javaProjects\\com.dio.test\\dio_test\\src\\main\\resources\\keys\\"))));
        CryptoLib cryptoLibService = new CryptoLib(new CryptoKeyStore(new CryptoFileService()));
        String decrStr = cryptoLibService.decryptString("uar", encrStr);
        //String decrStr = cryptoLibService.decryptStringWithKey(keys.getPrivate(), encrStr);

        if("uar".equals(decrStr)) System.out.println("result");


    }

}

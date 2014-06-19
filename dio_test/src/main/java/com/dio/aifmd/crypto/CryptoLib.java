package com.dio.aifmd.crypto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Idea origin is from
 * http://www.herongyang.com/Cryptography/JCE-Cipher-Secret-Key-Encryption-Sample-Program.html
 */
public class CryptoLib {
    public static final String ALGORITHM = "RSA";
    public static final String TRANSFORMATION_FORMAT = "UTF-8";
    private final CryptoKeyStore cryptoKeyStore;
    private final Logger logger = LogManager.getLogger(CryptoLib.class);

    public CryptoLib(CryptoKeyStore cryptoKeyStore) {
        this.cryptoKeyStore = cryptoKeyStore;
    }

    public Boolean privateKeyExists(String keyName) {
        return cryptoKeyStore.privateKeyExists(keyName);
    }

    public Boolean publicKeyExists(String keyName) {
        return cryptoKeyStore.publicKeyExists(keyName);
    }

    public byte[] encryptStringWithKey(PublicKey keyContent, String valueToEncrypt) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyContent);
            cipherText = cipher.doFinal(valueToEncrypt.getBytes(TRANSFORMATION_FORMAT));
        } catch (Exception e) {
            logger.error(new StringBuilder("Encrypt error: ").append(e));
        }
        return cipherText;
    }

    public byte[] encryptString(String key, String valueToEncrypt) {
        return encryptStringWithKey(cryptoKeyStore.getPublicKey(key), valueToEncrypt);
    }

    public String decryptString(String key, byte[] valueToDecrypt) {
        return decryptStringWithKey(cryptoKeyStore.getPrivateKey(key), valueToDecrypt);
    }

    public String decryptStringWithKey(PrivateKey key, byte[] valueToDecrypt) {
        String decryptedText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            decryptedText = new String(cipher.doFinal(valueToDecrypt), TRANSFORMATION_FORMAT);
        } catch (Exception e) {
            logger.error(new StringBuilder("Decrypt error: ").append(e));
        }
        return decryptedText;
    }

}
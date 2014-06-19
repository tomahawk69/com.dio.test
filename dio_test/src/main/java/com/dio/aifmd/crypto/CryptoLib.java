package com.dio.aifmd.crypto;

import com.dio.aifmd.crypto.CryptoKeyStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/**
 * http://www.herongyang.com/Cryptography/JCE-Cipher-Secret-Key-Encryption-Sample-Program.html
 */
public class CryptoLib {
    public static final String ALGORITHM = "RSA";
    public static final String TRANSFORMATION_FORMAT = "UTF-8";
    private final CryptoKeyStore cryptoKeyStore;
    private Map<String, String> keys;
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
            logger.error("Encrypt error: " + e);
            e.printStackTrace();
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
        byte[] dectyptedText;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(valueToDecrypt);
            return new String(dectyptedText, TRANSFORMATION_FORMAT);
        } catch (Exception ex) {
            logger.error("Decrypt error: " + ex);
            ex.printStackTrace();
        }
        return null;
    }

}
package com.dio.aifmd.crypto;

/**
 * Created by iovchynnikov on 6/13/2014.
 */
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.crypto.Cipher;

/**
 * @author JavaDigest+me
 * http://www.herongyang.com/Cryptography/JCE-Cipher-Secret-Key-Encryption-Sample-Program.html
 *
 */
public class CryptoLib {
    public static final String ALGORITHM = "RSA";

    private final CryptoKeyStore cryptoKeyStore;
    private Map<String, String> keys;
    private final Logger logger = Logger.getLogger(CryptoLib.class);

    public CryptoLib(CryptoKeyStore cryptoKeyStore) {
        this.cryptoKeyStore = cryptoKeyStore;
    }

//    /**
//     * Generate key which contains a pair of private and public key using 1024
//     * bytes. Store the set of keys in Prvate.key and Public.key files.
//     *
//     * @throws NoSuchAlgorithmException
//     * @throws IOException
//     * @throws FileNotFoundException
//     */
//
//    /**
//     * The method checks if the pair of public and private key has been generated.
//     *
//     * @return flag indicating if the pair of keys were generated.
//     */
//
//    public static boolean areKeysPresent() {
//        exists()
//
//
//        File privateKey = new File(PRIVATE_KEY_FILE);
//        File publicKey = new File(PUBLIC_KEY_FILE);
//
//        if (privateKey.exists() && publicKey.exists()) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Encrypt the plain text using public key.
//     *
//     * @param text
//     *          : original plain text
//     * @param key
//     *          :The public key
//     * @return Encrypted text
//     * @throws java.lang.Exception
//     */
//    public static byte[] encrypt(String text, PublicKey key) {
//        byte[] cipherText = null;
//        try {
//            // get an RSA cipher object and print the provider
//            final Cipher cipher = Cipher.getInstance(ALGORITHM);
//            // encrypt the plain text using the public key
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            cipherText = cipher.doFinal(text.getBytes());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cipherText;
//    }
//
//    /**
//     * Decrypt text using private key.
//     *
//     * @param text
//     *          :encrypted text
//     * @param key
//     *          :The private key
//     * @return plain text
//     * @throws java.lang.Exception
//     */
//    public static String decrypt(byte[] text, PrivateKey key) {
//        byte[] dectyptedText = null;
//        try {
//            // get an RSA cipher object and print the provider
//            final Cipher cipher = Cipher.getInstance(ALGORITHM);
//
//            // decrypt the text using the private key
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            dectyptedText = cipher.doFinal(text);
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return new String(dectyptedText);
//    }
//
//    /**
//     * Test the EncryptionUtil
//     */
//    public static void main(String[] args) {
//
//        try {
//
//            // Check if the pair of keys are present else generate those.
//            if (!areKeysPresent()) {
//                // Method generates a pair of keys using the RSA algorithm and stores it
//                // in their respective files
//                generateKey();
//            }
//
//            final String originalText = "Text to be encrypted ";
//            ObjectInputStream inputStream = null;
//
//            // Encrypt the string using the public key
//            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
//            final PublicKey publicKey = (PublicKey) inputStream.readObject();
//            final byte[] cipherText = encrypt(originalText, publicKey);
//
//            // Decrypt the cipher text using the private key.
//            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
//            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
//            final String plainText = decrypt(cipherText, privateKey);
//
//            // Printing the Original, Encrypted and Decrypted Text
//            System.out.println("Original Text: " + originalText);
//            System.out.println("Encrypted Text: " +cipherText.toString());
//            System.out.println("Decrypted Text: " + plainText);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    public Boolean privateKeyExists(String keyName) {
        return cryptoKeyStore.privateKeyExists(keyName);
    }

    public Boolean publicKeyExists(String keyName) {
        return cryptoKeyStore.publicKeyExists(keyName);
    }

    public String encryptStringWithKey(PublicKey keyContent, String decryptedString) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keyContent);
            cipherText = cipher.doFinal(decryptedString.getBytes());
        } catch (Exception e) {
            logger.error("Encrypt error: " + e);
            e.printStackTrace();
        }
        return cipherText.toString();
    }

    public String encryptString(String key, String decryptedString) {
        return encryptStringWithKey(cryptoKeyStore.getPublicKey(key), decryptedString);
    }

    public String decryptString(String key, String cryptedString) {
        return decryptStringWithKey(cryptoKeyStore.getPrivateKey(key), cryptedString);
    }

    public String decryptStringWithKey(PrivateKey key, String cryptedString) {
        byte[] dectyptedText = null;
        try {
            // get an RSA cipher object and print the provider
            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            // decrypt the text using the private key
            cipher.init(Cipher.DECRYPT_MODE, key);
            dectyptedText = cipher.doFinal(cryptedString.getBytes());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }

}
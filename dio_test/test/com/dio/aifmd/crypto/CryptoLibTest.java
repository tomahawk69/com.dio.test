package com.dio.aifmd.crypto;

import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoLibTest {


    @Test
    public void privateKeyExistsPositiveTest() {
        String keyName = "key";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(cryptoKeyStore);

        when(cryptoKeyStore.privateKeyExists(keyName)).thenReturn(true);

        Boolean result = cryptoLib.privateKeyExists(keyName);

        assertTrue(result);

        verify(cryptoKeyStore).privateKeyExists(keyName);
    }

    @Test
    public void privateKeyExistsNegativeTest() {
        String keyName = "key";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(cryptoKeyStore);

        when(cryptoKeyStore.privateKeyExists(keyName)).thenReturn(false);

        Boolean result = cryptoLib.privateKeyExists(keyName);

        assertFalse(result);

        verify(cryptoKeyStore).privateKeyExists(keyName);
    }

    @Test
    public void publicKeyExistsPositiveTest() {
        String keyName = "key";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(cryptoKeyStore);

        when(cryptoKeyStore.publicKeyExists(keyName)).thenReturn(true);

        Boolean result = cryptoLib.publicKeyExists(keyName);

        assertTrue(result);

        verify(cryptoKeyStore).publicKeyExists(keyName);
    }

    @Test
    public void publicKeyExistsNegativeTest() {
        String keyName = "key";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(cryptoKeyStore);

        when(cryptoKeyStore.publicKeyExists(keyName)).thenReturn(false);

        Boolean result = cryptoLib.publicKeyExists(keyName);

        assertFalse(result);

        verify(cryptoKeyStore).publicKeyExists(keyName);
    }

    @Test
    public void encryptStringTest() {
        String keyName = "key";
        String sourceString = "sourceString";
        byte[] encryptedString = "encryptedString".getBytes();
        PublicKey mockPublicKey = mock(PublicKey.class);

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = spy(new CryptoLib(cryptoKeyStore));

        when(cryptoKeyStore.getPublicKey(keyName)).thenReturn(mockPublicKey);
        doReturn(encryptedString).when(cryptoLib).encryptStringWithKey(mockPublicKey, sourceString);

        byte[] resultEncrypted = cryptoLib.encryptString(keyName, sourceString);

        assertEquals(encryptedString, resultEncrypted);

    }

    @Test
    public void decryptStringTest() {
        String keyName = "key";
        byte[] sourceString = "sourceString".getBytes();
        String decryptedString = "encryptedString";
        PrivateKey mockPrivateKey = mock(PrivateKey.class);

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = spy(new CryptoLib(cryptoKeyStore));

        when(cryptoKeyStore.getPrivateKey(keyName)).thenReturn(mockPrivateKey);
        doReturn(decryptedString).when(cryptoLib).decryptStringWithKey(mockPrivateKey, sourceString);

        String resultDecrypted = cryptoLib.decryptString(keyName, sourceString);

        assertEquals(decryptedString, resultDecrypted);

    }

    @Test
    public void encryptDecryptStringWithRealKeyTest() throws Exception {
        String algorhytm = "RSA";
        String decryptedString = "This is a test";

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorhytm);
        keyGen.initialize(1024);
        KeyPair key = keyGen.generateKeyPair();

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = spy(new CryptoLib(cryptoKeyStore));

        byte[] encryptedString = cryptoLib.encryptStringWithKey(key.getPublic(), decryptedString);
        String resultedString = cryptoLib.decryptStringWithKey(key.getPrivate(), encryptedString);

        assertEquals(decryptedString, resultedString);
    }
}
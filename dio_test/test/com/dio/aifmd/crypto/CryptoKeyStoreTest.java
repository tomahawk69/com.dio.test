package com.dio.aifmd.crypto;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CryptoKeyStoreTest {

    @Test
    public void testGetPrivateKey() throws Exception {
        String keyName = "key";
        String keyContent = "keyContent";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        when(service.loadPrivateKey(keyName)).thenReturn(keyContent);

        cryptoKeyStore.loadPrivateKey(keyName);
        String result = cryptoKeyStore.getPrivateKey(keyName);

        assertEquals(keyContent, result);
    }

    @Test
    public void testGetPublicKey() throws Exception {
        String keyName = "key";
        String keyContent = "keyContent";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        when(service.loadPublicKey(keyName)).thenReturn(keyContent);

        cryptoKeyStore.loadPublicKey(keyName);
        String result = cryptoKeyStore.getPublicKey(keyName);

        assertEquals(keyContent, result);
    }

    @Test
    public void testGetPrivateKeyNull() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        String result = cryptoKeyStore.getPrivateKey(keyName);

        assertNull(result);
    }

    @Test
    public void testGetPublicKeyNull() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        String result = cryptoKeyStore.getPublicKey(keyName);

        assertNull(result);
    }

    @Test
    public void testPrivateKeyExistsFalse() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        Boolean result = cryptoKeyStore.privateKeyExists(keyName);

        assertFalse(result);

    }

    @Test
    public void testPublicKeyExistsFalse() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        Boolean result = cryptoKeyStore.publicKeyExists(keyName);

        assertFalse(result);

    }

    @Test
    public void testPrivateKeyExistsTrue() throws Exception {
        String keyName = "key";
        String keyContent = "keyContent";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        when(service.loadPrivateKey(keyName)).thenReturn(keyContent);

        cryptoKeyStore.loadPrivateKey(keyName);
        Boolean result = cryptoKeyStore.privateKeyExists(keyName);

        assertTrue(result);

        verify(service).loadPrivateKey(keyName);

    }

    @Test
    public void testPublicKeyExistsTrue() throws Exception {
        String keyName = "key";
        String keyContent = "keyContent";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        when(service.loadPublicKey(keyName)).thenReturn(keyContent);

        cryptoKeyStore.loadPublicKey(keyName);
        Boolean result = cryptoKeyStore.publicKeyExists(keyName);

        assertTrue(result);

        verify(service).loadPublicKey(keyName);

    }

    @Test
    public void testLoadPrivateKey() throws Exception {
        String keyName = "key";
        String keyContent = "keyContent";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        when(service.loadPrivateKey(keyName)).thenReturn(keyContent);

        Boolean result = cryptoKeyStore.loadPrivateKey(keyName);
        String resultKey = cryptoKeyStore.getPrivateKey(keyName);

        assertTrue(result);
        assertEquals(keyContent, resultKey);

        verify(service).loadPrivateKey(keyName);
    }

    @Test
    public void testLoadPublicKey() throws Exception {
        String keyName = "key";
        String keyContent = "keyContent";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        when(service.loadPublicKey(keyName)).thenReturn(keyContent);

        Boolean result = cryptoKeyStore.loadPublicKey(keyName);
        String resultKey = cryptoKeyStore.getPublicKey(keyName);

        assertTrue(result);
        assertEquals(keyContent, resultKey);

        verify(service).loadPublicKey(keyName);
    }
}
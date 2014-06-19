package com.dio.aifmd.crypto;

import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoKeyStoreTest {

    @Test
    public void getPrivateKeyTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PrivateKey mockKey = mock(PrivateKey.class);
        when(service.loadPrivateKey(keyName)).thenReturn(mockKey);

        PrivateKey result = cryptoKeyStore.getPrivateKey(keyName);

        assertEquals(mockKey, result);
    }

    @Test
    public void getPrivateKeyTwiceTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PrivateKey mockKey = mock(PrivateKey.class);
        when(service.loadPrivateKey(keyName)).thenReturn(mockKey);

        PrivateKey result1 = cryptoKeyStore.getPrivateKey(keyName);
        PrivateKey result2 = cryptoKeyStore.getPrivateKey(keyName);

        assertEquals(mockKey, result1);
        assertEquals(mockKey, result2);

        verify(service, times(1)).loadPrivateKey(keyName);
    }

    @Test
    public void getPublicKeyTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PublicKey mockKey = mock(PublicKey.class);
        when(service.loadPublicKey(keyName)).thenReturn(mockKey);

        PublicKey result = cryptoKeyStore.getPublicKey(keyName);

        assertEquals(mockKey, result);
    }

    @Test
    public void getPublicKeyTwiceTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PublicKey mockKey = mock(PublicKey.class);
        when(service.loadPublicKey(keyName)).thenReturn(mockKey);

        PublicKey result1 = cryptoKeyStore.getPublicKey(keyName);
        PublicKey result2 = cryptoKeyStore.getPublicKey(keyName);

        assertEquals(mockKey, result1);
        assertEquals(mockKey, result2);

        verify(service, times(1)).loadPublicKey(keyName);
    }

    @Test
    public void privateKeyExistsTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PrivateKey mockKey = mock(PrivateKey.class);
        when(service.loadPrivateKey(keyName)).thenReturn(mockKey);

        cryptoKeyStore.loadPrivateKey(keyName);
        Boolean result = cryptoKeyStore.privateKeyExists(keyName);

        assertTrue(result);
    }

    @Test
    public void privateKeyExistsNegativeTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PrivateKey mockKey = mock(PrivateKey.class);
        when(service.loadPrivateKey(keyName)).thenReturn(mockKey);

        Boolean result = cryptoKeyStore.privateKeyExists(keyName);

        assertFalse(result);
    }

    @Test
    public void publicKeyExistsTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PublicKey mockKey = mock(PublicKey.class);
        when(service.loadPublicKey(keyName)).thenReturn(mockKey);

        cryptoKeyStore.loadPublicKey(keyName);
        Boolean result = cryptoKeyStore.publicKeyExists(keyName);

        assertTrue(result);
    }

    @Test
    public void publicKeyExistsNegativeTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PublicKey mockKey = mock(PublicKey.class);
        when(service.loadPublicKey(keyName)).thenReturn(mockKey);

        Boolean result = cryptoKeyStore.publicKeyExists(keyName);

        assertFalse(result);
    }

    @Test
    public void loadPrivateKeyTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PrivateKey mockKey = mock(PrivateKey.class);
        when(service.loadPrivateKey(keyName)).thenReturn(mockKey);

        Boolean result = cryptoKeyStore.loadPrivateKey(keyName);

        assertTrue(result);
    }

    @Test
    public void loadPublicKeyTest() throws Exception {
        String keyName = "key";

        CryptoFileService service = mock(CryptoFileService.class);
        CryptoKeyStore cryptoKeyStore = new CryptoKeyStore(service);

        PublicKey mockKey = mock(PublicKey.class);
        when(service.loadPublicKey(keyName)).thenReturn(mockKey);

        cryptoKeyStore.loadPublicKey(keyName);
        Boolean result = cryptoKeyStore.loadPublicKey(keyName);

        assertTrue(result);
    }


}
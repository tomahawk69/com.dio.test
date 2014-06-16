package com.dio.aifmd;

import com.dio.aifmd.crypto.CryptoKeyStore;
import com.dio.aifmd.crypto.CryptoLib;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoLibTest {

    @Test
    public void isPrivateKeyExistsTestPositive() {

        String keyName = "key";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(cryptoKeyStore);

        when(cryptoKeyStore.privateKeyExists(keyName)).thenReturn(true);

        Boolean result = cryptoLib.privateKeyExists(keyName);

        assertTrue(result);

        verify(cryptoKeyStore).privateKeyExists(keyName);
    }

    @Test
    public void isPublicKeyExistsTestPositive() {

        String keyName = "key";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(cryptoKeyStore);

        when(cryptoKeyStore.publicKeyExists(keyName)).thenReturn(true);

        Boolean result = cryptoLib.publicKeyExists(keyName);

        assertTrue(result);

        verify(cryptoKeyStore).publicKeyExists(keyName);
    }

    @Test
    public void decryptStringTest() {
        String keyName = "key";
        String keyContent = "keyFileName";
        String cryptedString = "Crypted string";
        String decryptedString = "Decrypted string";

        CryptoKeyStore cryptoKeyStore = mock(CryptoKeyStore.class);
        CryptoLib cryptoLib = spy(new CryptoLib(cryptoKeyStore));

        when(cryptoKeyStore.privateKeyExists(keyName)).thenReturn(true);
        when(cryptoKeyStore.getPrivateKey(keyName)).thenReturn(keyContent);
        doReturn(decryptedString).when(cryptoLib).decryptString(keyContent, cryptedString);

        String result = cryptoLib.decryptStringWithKey(keyName, cryptedString);

        assertEquals(decryptedString, result);

        verify(cryptoKeyStore).privateKeyExists(keyName);
        verify(cryptoKeyStore).getPrivateKey(keyName);
    }

}
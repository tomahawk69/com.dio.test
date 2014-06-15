package com.dio.aifmd;

import com.dio.aifmd.crypto.CryptoLib;
import com.dio.aifmd.crypto.FileService;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CryptoLibTest {

    @Test
    public void isKeyExistsTestPositive() {

        String keyName = "key";

        FileService fileService = mock(FileService.class);
        KeyStore keyStore = mock(KeyStore.class);
        CryptoLib cryptoLib = new CryptoLib(fileService);


        when(fileService.isKeyExists(keyName)).thenReturn(true);

        Boolean result = cryptoLib.isKeyExists(keyName);

        assertTrue(result);

        verify(fileService).isKeyExists(keyName);
    }

    @Test
    public void loadKeyTest() {

        String keyName = "key";
        String keyContent = "keyFileName";

        FileService fileService = mock(FileService.class);
        CryptoLib cryptoLib = new CryptoLib(fileService);


        when(fileService.isKeyExists(keyName)).thenReturn(true);
        when(fileService.getKey(keyName)).thenReturn(keyContent);

        Boolean result = cryptoLib.loadKey(keyName);
        Boolean result1 = cryptoLib.isKeyLoaded(keyName);

        assertTrue(result);

        verify(fileService).isKeyExists(keyName);
        verify(fileService).getKey(keyName);
    }

    @Test
    public void decryptStringTest() {


    }



}
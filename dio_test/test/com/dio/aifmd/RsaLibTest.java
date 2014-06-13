package com.dio.aifmd;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RsaLibTest {

    @Test
    public void isKeyExistsTestPositive() {

        String keyName = "key";

        RsaFileService fileService = mock(RsaFileService.class);
        RsaLib rsaLib = new RsaLib(fileService);

        when(fileService.isKeyExists(keyName)).thenReturn(true);

        Boolean result = rsaLib.isKeyExists(keyName);

        assertTrue(result);

        verify(fileService).isKeyExists(keyName);
    }

    @Test
    public void loadKeyTest() {

        String keyName = "key";
        String keyContent = "keyFileName";

        RsaFileService fileService = mock(RsaFileService.class);
        RsaLib rsaLib = new RsaLib(fileService);


        when(fileService.isKeyExists(keyName)).thenReturn(true);
        when(fileService.getKey(keyName)).thenReturn(keyContent);

        Boolean result = rsaLib.loadKey(keyName);
        Boolean result1 = rsaLib.isKeyLoaded(keyName);

        assertTrue(result);

        verify(fileService).isKeyExists(keyName);
        verify(fileService).getKey(keyName);
    }

    @Test
    public void decryptStringTest() {


    }



}
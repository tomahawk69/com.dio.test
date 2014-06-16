package com.dio.aifmd.crypto;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoFileServiceTest {

    @Test
    public void loadPrivateKeyTest() {
        String keyName = "key";
        String keyContent = "keyContent";
        Path mockPath = mock(Path.class);
        Path mockFilePath = mock(Path.class);

        CryptoFileService service = spy(new CryptoFileService(mockPath));
        doReturn(mockFilePath).when(service).constructPrivateKeyName(keyName);
        doReturn(keyContent).when(service).loadFileContent(mockFilePath);

        String result = service.loadPrivateKey(keyName);

        assertEquals(keyContent, result);

        verify(service).constructPrivateKeyName(keyName);
        verify(service).loadFileContent(mockFilePath);

    }

    @Test
    public void loadPublicKeyTest() {
        String keyName = "key";
        String keyContent = "keyContent";
        Path mockPath = mock(Path.class);
        Path mockFilePath = mock(Path.class);

        CryptoFileService service = spy(new CryptoFileService(mockPath));
        doReturn(mockFilePath).when(service).constructPublicKeyName(keyName);
        doReturn(keyContent).when(service).loadFileContent(mockFilePath);

        String result = service.loadPublicKey(keyName);

        assertEquals(keyContent, result);

        verify(service).constructPublicKeyName(keyName);
        verify(service).loadFileContent(mockFilePath);

    }

    @Test
    public void constructPrivateKeyNameTest() {
        String keyName = "key";
        Path mockPath = mock(Path.class);
        Path mockFilePath = mock(Path.class);

        CryptoFileService service = spy(new CryptoFileService(mockPath));
        doReturn(mockFilePath).when(service).constructFileName(keyName, service.PRIVATE_KEY_EXT);

        Path result = service.constructPrivateKeyName(keyName);

        assertEquals(mockFilePath, result);

        verify(service).constructFileName(keyName, service.PRIVATE_KEY_EXT);

    }


    @Test
    public void constructPublicKeyNameTest() {
        String keyName = "key";
        Path mockPath = mock(Path.class);
        Path mockFilePath = mock(Path.class);

        CryptoFileService service = spy(new CryptoFileService(mockPath));
        doReturn(mockFilePath).when(service).constructFileName(keyName, service.PUBLIC_KEY_EXT);

        Path result = service.constructPublicKeyName(keyName);

        assertEquals(mockFilePath, result);

        verify(service).constructFileName(keyName, service.PUBLIC_KEY_EXT);

    }

    @Test
    public void constructFileNameTest() {
        Path mockFilePath = mock(Path.class);
        String dbPath = "dbPath";
        String key = "key";
        String ext = ".ext";
        Path expectedResult = Paths.get(dbPath, key + ext);

        CryptoFileService service = spy(new CryptoFileService(Paths.get(dbPath)));

        Path result = service.constructFileName(key, ext);

        assertEquals(expectedResult, result);

    }


}
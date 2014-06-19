package com.dio.aifmd.crypto;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CryptoFileServiceTest {

    @Test
    public void loadPrivateKeyTestFromDirectory() {
        String keyName = "key";
        PrivateKey keyContent = mock(PrivateKey.class);
        Path keyPath = Paths.get("path");
        Path mockFilePath = mock(Path.class);

        CryptoFileService service = spy(new CryptoFileService(keyPath));
        doReturn(mockFilePath).when(service).constructFileName(keyName, service.PRIVATE_KEY_EXT);
        doReturn(keyContent).when(service).loadFileContent(mockFilePath);

        PrivateKey result = service.loadPrivateKey(keyName);

        assertEquals(keyContent, result);
    }

    @Test
    public void loadPrivateKeyTestFromClasspath() {
        String keyName = "key";
        String fileCpPath = "keyCpPath";
        PrivateKey keyContent = mock(PrivateKey.class);

        CryptoFileService service = spy(new CryptoFileService());
        doReturn(fileCpPath).when(service).constructFileNameFromCP(keyName, service.PRIVATE_KEY_EXT);
        doReturn(keyContent).when(service).loadFileContentFromCP(fileCpPath);

        PrivateKey result = service.loadPrivateKey(keyName);

        assertEquals(keyContent, result);
    }

    @Test
    public void loadPublicKeyTestFromDirectory() {
        String keyName = "key";
        PublicKey keyContent = mock(PublicKey.class);
        Path keyPath = Paths.get("path");
        Path mockFilePath = mock(Path.class);

        CryptoFileService service = spy(new CryptoFileService(keyPath));
        doReturn(mockFilePath).when(service).constructFileName(keyName, service.PUBLIC_KEY_EXT);
        doReturn(keyContent).when(service).loadFileContent(mockFilePath);

        PublicKey result = service.loadPublicKey(keyName);

        assertEquals(keyContent, result);

    }

    @Test
    public void loadPublicKeyTestFromClasspath() {
        String keyName = "key";
        String fileCpPath = "keyCpPath";
        PublicKey keyContent = mock(PublicKey.class);

        CryptoFileService service = spy(new CryptoFileService());
        doReturn(fileCpPath).when(service).constructFileNameFromCP(keyName, service.PUBLIC_KEY_EXT);
        doReturn(keyContent).when(service).loadFileContentFromCP(fileCpPath);

        PublicKey result = service.loadPublicKey(keyName);

        assertEquals(keyContent, result);
    }

    @Test
    public void constructFileNamePrivateTest() {
        String keyName = "key";
        Path dbPath = Paths.get("path");

        CryptoFileService service = spy(new CryptoFileService(dbPath));
        Path filePath = Paths.get(dbPath.toString(), keyName + service.PRIVATE_KEY_EXT);
        Path result = service.constructFileName(keyName, service.PRIVATE_KEY_EXT);

        assertEquals(filePath, result);

    }

    @Test
    public void constructFileNamePublicTest() {
        String keyName = "key";
        Path dbPath = Paths.get("path");

        CryptoFileService service = spy(new CryptoFileService(dbPath));
        Path filePath = Paths.get(dbPath.toString(), keyName + service.PUBLIC_KEY_EXT);
        Path result = service.constructFileName(keyName, service.PUBLIC_KEY_EXT);

        assertEquals(filePath, result);

    }

    @Test
    public void constructFileNameFromCpPrivateTest() {
        String keyName = "key";

        CryptoFileService service = spy(new CryptoFileService());
        String filePathCp = service.CLASSPATH_DIRECTORY + keyName + service.PRIVATE_KEY_EXT;
        String result = service.constructFileNameFromCP(keyName, service.PRIVATE_KEY_EXT);

        assertEquals(filePathCp, result);

    }


    @Test
    public void constructFileNameFromCpPublicTest() {
        String keyName = "key";

        CryptoFileService service = spy(new CryptoFileService());
        String filePathCp = service.CLASSPATH_DIRECTORY + keyName + service.PUBLIC_KEY_EXT;
        String result = service.constructFileNameFromCP(keyName, service.PUBLIC_KEY_EXT);

        assertEquals(filePathCp, result);

    }

    @Test
    public void loadFileContentTest() throws IOException, ClassNotFoundException {
        String keyName = "key";
        Path dbPath = mock(Path.class);
        Path mockFilePath = mock(Path.class);
        Key mockKey = mock(Key.class);

        CryptoFileService service = spy(new CryptoFileService(dbPath));

        doReturn(mockKey).when(service).readKeyFromFile(mockFilePath);

        Key keyResult = service.loadFileContent(mockFilePath);

        assertEquals(mockKey, keyResult);
    }

    @Test
    public void loadFileContentCpTest() throws IOException, ClassNotFoundException {
        String keyName = "key";
        String keyFileName = "keyFileName";
        Key mockKey = mock(Key.class);

        CryptoFileService service = spy(new CryptoFileService());

        doReturn(mockKey).when(service).readKeyFromFileCp(keyFileName);

        Key keyResult = service.loadFileContentFromCP(keyFileName);

        assertEquals(mockKey, keyResult);
    }


    @Test
    public void privateFileExistsTest() {
        Path mockFilePath = mock(Path.class);
        String dbPath = "dbPath";
        String key = "key";
        String ext = ".ext";

        CryptoFileService service = spy(new CryptoFileService(Paths.get(dbPath)));
        doReturn(mockFilePath).when(service).constructFileName(key, service.PRIVATE_KEY_EXT);
        doReturn(true).when(service).fileExists(mockFilePath);

        Boolean result = service.privateKeyExists(key);

        assertTrue(result);

    }

    @Test
    public void publicFileExistsTest() {
        Path mockFilePath = mock(Path.class);
        String dbPath = "dbPath";
        String key = "key";
        String ext = ".ext";

        CryptoFileService service = spy(new CryptoFileService(Paths.get(dbPath)));
        doReturn(mockFilePath).when(service).constructFileName(key, service.PUBLIC_KEY_EXT);
        doReturn(true).when(service).fileExists(mockFilePath);

        Boolean result = service.publicKeyExists(key);

        assertTrue(result);

    }


}
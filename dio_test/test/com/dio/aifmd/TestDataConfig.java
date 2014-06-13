package com.dio.aifmd;

import com.dio.calendar.Entry;
import com.dio.test.PojoPerson;
import com.dio.test.PojoPersonWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.dio.calendar.Entry.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by iovchynnikov on 5/22/14.
 */
public class TestDataConfig {
    private DataConfig config, configReal;
    private DataConnect2 dataReal;
    private DataOperations operationsMock, operationsReal;

    @Before
    public void setUp() {

        operationsMock = mock(DataOperations.class);
        config = spy(new DataConfig(operationsMock));

        dataReal = new DataConnect2(new ConnectionInfo("idas_dev", "dio32", 2638, "idas_dev", "AIFMD_DEV", "AIFMD_DEV1234!"), "SQL Anywhere 12", "jdbc:sqlanywhere:");
        operationsReal = spy(new DataOperations(dataReal));
        configReal = spy(new DataConfig(operationsReal));
    }

    @Test
    public void testWrite() throws Exception {
        PojoPerson inputObject = new PojoPerson("first", "last", "next");
        when(operationsMock.performInsertParams(anyString(), anyString(), anyString())).thenReturn(1);

        config.write(inputObject);

        verify(config).getMarshaller(PojoPersonWrapper.class);
        verify(operationsMock).performInsertParams(anyString(), anyString(), anyString());
    }

    @Test
    public void testWriteReal() throws Exception {
        PojoPerson inputObject = new PojoPerson("first", "last", "next");
        String[] result = new String[0];

        configReal.write(inputObject);

        configReal.delete(inputObject);

        verify(configReal).getMarshaller(PojoPersonWrapper.class);
        verify(operationsReal).performInsertParams(anyString(), anyString(), anyString());

    }

    @Test
    public void testReadReal() throws Exception {
        PojoPerson inputObject = new PojoPerson("first", "last", "next");
        String[] result = new String[0];

        configReal.write(inputObject);

        PojoPerson resultObject = configReal.read(inputObject.toString());

        assertEquals(inputObject, resultObject);

        verify(configReal).getUnmarshaller(PojoPersonWrapper.class);
        verify(operationsReal).performSelectParams(anyString(), anyString());

    }


}

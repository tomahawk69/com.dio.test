package com.dio.test;

import org.junit.Test;
import java.io.File;
import java.nio.file.Files;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;


/**
 * Created by yur on 15.05.2014.
 */
public class TestSerialize {

    private void cleanup(String fileName) {
        File file = new File(fileName);

        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }
    }

    @Test
    public void testSaveObject () {
        String fileName = "testSaveObject.ser";

        Object inputObject = new PojoPerson("First", "Middle", "Last");

        boolean resultSave = PojoPersonUtils3.saveObject(inputObject, fileName);
        assertThat(resultSave, is(true));

        cleanup(fileName);
    }

    @Test
    public void testSaveObjects() {
        String fileName = "testSaveObjects.ser";

        Object inputObject1 = new PojoPerson("First1", "Middle1", "Last1");
        Object inputObject2 = new PojoPerson("First2", "Middle2", "Last2");

        boolean resultSave = PojoPersonUtils3.saveObjects(fileName, inputObject1, inputObject2);

        assertThat(resultSave, is(true));

        cleanup(fileName);
    }

    @Test
    public void testSaveObjects2() {
        String fileName = "testSaveObjects2.ser";

        Object inputObject1 = new PojoPerson("First1", "Middle1", "Last1");
        Object inputObject2 = new PojoPerson("First2", "Middle2", "Last2");

        boolean resultSave = PojoPersonUtils3.saveObjects2(fileName, inputObject1, inputObject2);

        assertThat(resultSave, is(true));

        cleanup(fileName);
    }

    @Test
    public void testLoadObject () {
        String fileName = "testLoadObject.ser";

        Object inputObject = new PojoPerson("First", "Middle", "Last");

        boolean resultSave = PojoPersonUtils3.saveObject(inputObject, fileName);
        assertThat(resultSave, is(true));

        Object resultObject = PojoPersonUtils3.readObject(fileName);

        assertThat(inputObject, is(equalTo(resultObject)));

        cleanup(fileName);
    }

    @Test
    public void testLoadObjects () {
        String fileName = "testLoadObjects.ser";

        Object inputObject1 = new PojoPerson("First1", "Middle1", "Last1");
        Object inputObject2 = new PojoPerson("First2", "Middle2", "Last2");

        PojoPersonUtils3.saveObjects(fileName, inputObject1, inputObject2);

        List<Object> resultExpected;
        resultExpected = new ArrayList<>(Arrays.asList(inputObject1, inputObject2));
        List<Object> resultObjects = PojoPersonUtils3.readObjects(fileName);

        assertThat("mismatched", resultObjects, is(equalTo(resultExpected)));

        cleanup(fileName);
    }

    @Test
    public void testLoadObjects2 () {
        String fileName = "testLoadObjects2.ser";

        Object inputObject1 = new PojoPerson("First1", "Middle1", "Last1");
        Object inputObject2 = new PojoPerson("First2", "Middle2", "Last2");

        PojoPersonUtils3.saveObjects2(fileName, inputObject1, inputObject2);

        List<Object> resultExpected;
        resultExpected = new ArrayList<>(Arrays.asList(inputObject1, inputObject2));
        List<Object> resultObjects = PojoPersonUtils3.readObjects2(fileName);

        assertThat("mismatched", resultObjects, is(equalTo(resultExpected)));

        cleanup(fileName);
    }

    @Test
    public void testLoadObjectTransient () {
        String fileName = "testLoadObjectTransient.ser";

        PojoPerson inputObject = new PojoPerson("First", "Middle", "Last", EnumJob.DEVELOPER);

        PojoPersonUtils3.saveObject(inputObject, fileName);

        Object resultObject = (PojoPerson) PojoPersonUtils3.readObject(fileName);
        assertThat(resultObject, is(PojoPerson.class));

        PojoPerson resultPojo = (PojoPerson) resultObject;
        assertThat(inputObject.getJob(), not(equalTo(resultPojo.getJob())));

        resultPojo.setJob(inputObject.getJob());
        assertThat(inputObject, is(equalTo(resultPojo)));

        cleanup(fileName);
    }

}

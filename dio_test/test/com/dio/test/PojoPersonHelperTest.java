package com.dio.test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PojoPersonHelperTest {
    private PojoPersonUtils2 utils;
    private PojoPersonHelper helper;
    private PojoPerson[] persons1, persons2;

    @Before
    public void setUp() throws Exception {
        utils = mock(PojoPersonUtils2.class);
        helper = new PojoPersonHelper(utils);

        String firstName = "First";
        String middleName = "Middle";
        String lastName = "Last";

        persons1 = new PojoPerson[]{
                new PojoPerson(firstName, middleName, lastName),
                new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER),
        };
        persons2 = new PojoPerson[]{
                new PojoPerson(firstName, null, lastName),
                new PojoPerson(firstName, middleName, lastName, EnumJob.DIRECTOR),
                new PojoPerson(firstName.toUpperCase(), middleName, lastName, EnumJob.DEVELOPER),
                new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER),
        };

    }


    @Test
    public void testRemoveDupes() throws Exception {
        when(utils.joinDistinct(persons1, null)).thenReturn(persons2);
        PojoPerson[] result = helper.removeDupes(persons1);
        assertArrayEquals("Mock data wong answer failed", result, persons2);
        verify(utils, times(1));
    }
/*
    @Test
    public void testRemoveDupesBehaviour() throws Exception {
        when(utils.joinDistinct(persons1, null)).thenReturn(persons2);
        //verify(utils).joinDistinct(persons1, null);
        //assertArrayEquals("Mock data wong answer failed", helper.removeDupes(persons1), persons2);
        assertArrayEquals("Mock data wong answer failed", helper.removeDupes(persons1), persons2);
        verify(utils);
    }
*/
}
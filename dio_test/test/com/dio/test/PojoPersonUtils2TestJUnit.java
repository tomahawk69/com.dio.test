package com.dio.test;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by iovchynnikov on 4/23/14.
 */
public class PojoPersonUtils2TestJUnit {
    private PojoPersonUtils2 tester;
    private PojoPerson[] persons1, persons2;

    @Before
    public void setUp() throws Exception {
        tester = new PojoPersonUtils2();
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
    public void testJoinFull() throws Exception {
        PojoPerson[] resultExpected = new PojoPerson[] {
                persons1[0],
                persons1[1],
                persons2[0],
                persons2[1],
                persons2[2],
                persons2[3],
        },
        resultReceived = tester.joinFull(persons1, persons2);

        assertArrayEquals("Full join result does not equal to expected one", resultExpected, resultReceived);
    }

    @Test
    public void testJoinDistinct() throws Exception {
        PojoPerson[] resultExpected = new PojoPerson[] {
                persons1[0],
                persons1[1],
                persons2[0],
                persons2[1],
                persons2[2],
        },
                resultReceived = tester.joinDistinct(persons1, persons2);

        assertArrayEquals("Distinct join result does not equal to expected one", resultExpected, resultReceived);
    }

    @Test
    public void testJoinInner() throws Exception {
        PojoPerson[] resultExpected = new PojoPerson[] {
                persons1[1],
        },
                resultReceived = tester.joinInner(persons1, persons2);

        assertArrayEquals("Inner join result does not equal to expected one", resultExpected, resultReceived);
    }

    @Test
    public void testJoinInnerNull() throws Exception {
        PojoPerson[] resultExpected = new PojoPerson[] {
        },
                resultReceived = tester.joinInner(persons1, null);

        assertArrayEquals("Inner join with null result does not equal to expected one", resultExpected, resultReceived);
    }

    @Test
    public void testJoinOuter() throws Exception {
        PojoPerson[] resultExpected = new PojoPerson[] {
                persons1[0],
                persons2[0],
                persons2[1],
                persons2[2],
        },
                resultReceived = tester.joinOuter(persons1, persons2);

        assertArrayEquals("Outer join result does not equal to expected one", resultExpected, resultReceived);
    }

    @Test
    public void testContainsPositive() throws Exception {
        boolean result = tester.contains(persons1, persons1[1]);
        assertTrue("Positive contains test", result);

    }

    @Test
    public void testContainsNegative() throws Exception {
        boolean result = tester.contains(persons1, persons2[1]);
        assertFalse("Negative contains test", result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testContainsException() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        tester.contains(persons1, null);
    }

}

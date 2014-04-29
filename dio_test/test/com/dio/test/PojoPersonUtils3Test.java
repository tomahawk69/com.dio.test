package com.dio.test;

// delete old framework
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by iovchynnikov on 4/29/14.
 */
public class PojoPersonUtils3Test {
    private List<PojoPerson> persons1, persons2;
    private PojoPersonUtils3 testertester;


    @Before
    public void setUp() throws Exception {
        String firstName = "First";
        String middleName = "Middle";
        String lastName = "Last";

        persons1 = new ArrayList<PojoPerson>();
        persons1.add(new PojoPerson(firstName, middleName, lastName));
        persons1.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER));

        persons2 = new ArrayList<PojoPerson>();
        persons2.add(new PojoPerson(firstName, null, lastName));
        persons2.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DIRECTOR));
        persons2.add(new PojoPerson(firstName.toUpperCase(), middleName, lastName, EnumJob.DEVELOPER));
        persons2.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER));

        testertester = new PojoPersonUtils3();
    }

    @Test
    public void testJoinFull() throws Exception {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);

        resultExpected.addAll(persons1);
        resultExpected.addAll(persons2);

        List<PojoPerson> resultReceived = testertester.joinFull(persons1, persons2);

        assertTrue("Full join PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    @Test
    public void testDistinct() {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(persons1);

        List<PojoPerson> resultReceived = testertester.distinct(persons1);

        assertTrue("Distinct PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    @Test
    public void testDistinctPrevious() {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(persons1);
        resultExpected.add(persons2.get(0));
        resultExpected.add(persons2.get(1));
        resultExpected.add(persons2.get(2));


        List<PojoPerson> resultReceived = testertester.distinctPrevious(persons2, persons1);

        assertTrue("Distinct with previous PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinInner() throws Exception {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.add(persons1.get(1));

        List<PojoPerson> resultReceived = testertester.joinInner(persons1, persons2);

        assertTrue("Inner join PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinOuter() throws Exception {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.add(persons1.get(0));
        resultExpected.add(persons2.get(0));
        resultExpected.add(persons2.get(1));
        resultExpected.add(persons2.get(2));

        List<PojoPerson> resultReceived = testertester.joinOuter(persons1, persons2);

        assertTrue("Outer join PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    @Test
    public void testContainsPositive() throws Exception {
        boolean result = testertester.contains(persons1, persons1.get(1));
        assertTrue("Contains positive PojoPersonUnit3 test", result);

    }

    @Test
    public void testContainsNegative() throws Exception {
        boolean result = testertester.contains(persons1, persons2.get(1));
        assertFalse("Contains negative PojoPersonUnit3 test", result);

    }

    @Test
    public void testDistinctSpy() {
        PojoPersonUtils3 mocktester = spy(testertester);

        mocktester.distinct(persons1);
        verify(mocktester, times(1)).distinctPrevious(persons1, new ArrayList<PojoPerson>());
    }

}

package com.dio.test;

// delete old framework
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by iovchynnikov on 4/29/14.
 */
public class PojoPersonUtils3Test {
    private List<PojoPerson> personsVarious, personsSame, persons1, persons2;
    private PojoPersonUtils3 testertester;


    @Before
    public void setUp() throws Exception {
        String firstName = "First";
        String middleName = "Middle";
        String lastName = "Last";


        persons1 = new ArrayList<PojoPerson>();
        persons2 = new ArrayList<PojoPerson>();

        personsVarious = new ArrayList<>();
        personsVarious.add(new PojoPerson(firstName, middleName, lastName));
        personsVarious.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER));
        personsVarious.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DIRECTOR));
        personsVarious.add(new PojoPerson(firstName, null, lastName, EnumJob.DEVELOPER));
        personsVarious.add(new PojoPerson(firstName.toLowerCase(), middleName, lastName, EnumJob.DEVELOPER));

        personsSame = new ArrayList<>();
        personsSame.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER));
        personsSame.add(new PojoPerson(firstName, middleName, lastName, EnumJob.DEVELOPER));

        testertester = new PojoPersonUtils3();
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testJoinFull() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>(persons2);
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(list1);
        resultExpected.addAll(list2);

        List<PojoPerson> resultReceived = testertester.joinFull(list1, list2);

        assertTrue("Full join PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }


    @Test
    public void testJoinFullNull1() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        testertester.joinFull(list1, null);
    }

    @Test
    public void testJoinFullNull2() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        testertester.joinFull(null, list1);
    }

    @Test
    public void testJoinFullBlank1() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>();

        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(list1);
        resultExpected.addAll(list2);

        List<PojoPerson> resultReceived = testertester.joinFull(list1, list2);

        assertTrue("Full join PojoPersonUnit3 blank test 1", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinFullBlank2() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>();

        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(list1);
        resultExpected.addAll(list2);

        List<PojoPerson> resultReceived = testertester.joinFull(list1, list2);

        assertTrue("Full join PojoPersonUnit3 blank test 2", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinFullImmutable() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(personsSame);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>(personsVarious);

        List<PojoPerson> list1Back = new ArrayList<PojoPerson>(list1);
        List<PojoPerson> list2Back = new ArrayList<PojoPerson>(list2);

        testertester.joinFull(list2, list1);

        assertTrue("Full join PojoPersonUnit3 test immutable 1", list1.equals(list1Back));
        assertTrue("Full join PojoPersonUnit3 test immutable 2", list2.equals(list2Back));
    }



    // inner join tests

    @Test
    public void testJoinInner() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>();
        List<PojoPerson> list2 = new ArrayList<PojoPerson>();

        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();
        for (int i = 0; i < personsVarious.size(); i++) {
            if (i % 2 == 1)
                list2.add(personsVarious.get(i));
            else
                list1.add(personsVarious.get(i));
        }

        for (int i = 0; i < personsSame.size()/2; i++) {
            list1.add(personsSame.get(i*2));
            list2.add(personsSame.get(i*2+1));
            resultExpected.add(personsSame.get(i*2));
        }

        List<PojoPerson> resultReceived = testertester.joinInner(list1, list2);

        assertTrue("Inner join PojoPersonUnit3 test positive", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinInnerNegative() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>();
        List<PojoPerson> list2 = new ArrayList<PojoPerson>();

        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();
        for (int i = 0; i < personsVarious.size(); i++) {
            if (i % 2 == 1)
                list2.add(personsVarious.get(i));
            else
                list1.add(personsVarious.get(i));
        }

        List<PojoPerson> resultReceived = testertester.joinInner(list1, list2);

        assertTrue("Inner join PojoPersonUnit3 test negative", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinInnerBlank1() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(personsSame);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>();

        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();
        List<PojoPerson> resultReceived = testertester.joinInner(list1, list2);

        assertTrue("Inner join PojoPersonUnit3 test blank 1", resultReceived.equals(resultExpected));
    }

    @Test
    public void testJoinInnerBlank2() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(personsSame);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>();

        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();
        List<PojoPerson> resultReceived = testertester.joinInner(list2, list1);

        assertTrue("Inner join PojoPersonUnit3 test blank 2", resultReceived.equals(resultExpected));
    }


    @Test
    public void testJoinInnerImmutable() throws Exception {
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(personsSame);
        List<PojoPerson> list2 = new ArrayList<PojoPerson>(personsVarious);

        List<PojoPerson> list1Back = new ArrayList<PojoPerson>(list1);
        List<PojoPerson> list2Back = new ArrayList<PojoPerson>(list2);

        testertester.joinInner(list2, list1);

        assertTrue("Inner join PojoPersonUnit3 test immutable 1", list1.equals(list1Back));
        assertTrue("Inner join PojoPersonUnit3 test immutable 2", list2.equals(list2Back));
    }

    @Test
    public void testJoinInnerNull1() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        testertester.joinInner(list1, null);
    }

    @Test
    public void testJoinInnerNull2() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        List<PojoPerson> list1 = new ArrayList<PojoPerson>(persons1);
        testertester.joinInner(null, list1);
    }

    // Outer join

    //@Test
    public void testJoinOuter() throws Exception {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.add(persons1.get(0));
        resultExpected.add(persons2.get(0));
        resultExpected.add(persons2.get(1));
        resultExpected.add(persons2.get(2));

        List<PojoPerson> resultReceived = testertester.joinOuter(persons1, persons2);

        assertTrue("Outer join PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    //@Test
    public void testContainsPositive() throws Exception {
        boolean result = testertester.contains(persons1, persons1.get(1));
        assertTrue("Contains positive PojoPersonUnit3 test", result);

    }

    //@Test
    public void testContainsNegative() throws Exception {
        boolean result = testertester.contains(persons1, persons2.get(1));
        assertFalse("Contains negative PojoPersonUnit3 test", result);

    }

    //@Test
    public void testDistinctSpy() {
        PojoPersonUtils3 mocktester = spy(testertester);

        mocktester.distinct(persons1);
        verify(mocktester, times(1)).distinctPrevious(persons1, new ArrayList<PojoPerson>());
    }


    //@Test
    public void testDistinct() {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(persons1);

        List<PojoPerson> resultReceived = testertester.distinct(persons1);

        assertTrue("Distinct PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }

    //@Test
    public void testDistinctPrevious() {
        List<PojoPerson> resultExpected = new ArrayList<PojoPerson>();

        resultExpected.addAll(persons1);
        resultExpected.add(persons2.get(0));
        resultExpected.add(persons2.get(1));
        resultExpected.add(persons2.get(2));


        List<PojoPerson> resultReceived = testertester.distinctPrevious(persons2, persons1);

        assertTrue("Distinct with previous PojoPersonUnit3 test", resultReceived.equals(resultExpected));
    }


}

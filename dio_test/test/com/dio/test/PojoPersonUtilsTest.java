package com.dio.test;

import java.util.Arrays;


/**
 * Created by yur on 21.04.2014.
 */
public class PojoPersonUtilsTest  extends BaseTest {

    private static PojoPerson[] persons1, persons2;

    private static boolean joinFullTest() throws TestNotPassedException {
        prepareData();

        PojoPerson[] resultExpected = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] resultRecieved = PojoPersonUtils.joinFull(persons1, persons2);

        if (!Arrays.equals(resultExpected, resultRecieved)) {
            errorMessageArr("joinFullTest test 1", resultExpected, resultRecieved);
            return false;
        }

        resultExpected = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        resultRecieved = PojoPersonUtils.joinFull(persons2, persons1);

        if (!Arrays.equals(resultExpected, resultRecieved)) {
            errorMessageArr("joinFullTest test 2", resultExpected, resultRecieved);
            return false;
        }
        return true;
    }

    private static boolean joinDistinctTest() throws TestNotPassedException {
        prepareData();

        PojoPerson[] resultRecieved = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinDistinct(persons1, persons2);

        if (!Arrays.equals(re, resultRecieved)) {
            errorMessageArr("joinDistinctTest test 1", re, resultRecieved);
            return false;
        }

        resultRecieved = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last"),
        };
        re = PojoPersonUtils.joinDistinct(persons2, persons1);

        if (!Arrays.equals(re, resultRecieved)) {
            errorMessageArr("joinDistinctTest test 2", re, resultRecieved);
            return false;
        }
        return true;
    }

    private static boolean joinInnerTest() throws TestNotPassedException {
        prepareData();

        PojoPerson[] resultRecieved = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinInner(persons1, persons2);

        if (!Arrays.equals(re, resultRecieved)) {
            errorMessageArr("joinInnerTest test 1", re, resultRecieved);
            return false;
        }

        re = PojoPersonUtils.joinInner(persons2, persons1);

        if (!Arrays.equals(re, resultRecieved)) {
            errorMessageArr("joinInnerTest test 2", re, resultRecieved);
            return false;
        }
        return true;
    }

    private static boolean joinOuterTest() throws TestNotPassedException {
        prepareData();

        PojoPerson[] resultRecieved = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinOuter(persons1, persons2);

        if (!Arrays.equals(re, resultRecieved)) {
            errorMessageArr("joinOuterTest test 1", re, resultRecieved);
            return false;
        }

        resultRecieved = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last"),
        };
        re = PojoPersonUtils.joinOuter(persons2, persons1);

        if (!Arrays.equals(re, resultRecieved)) {
            errorMessageArr("joinOuterTest test 2", re, resultRecieved);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        boolean result = true;
        try {
            joinFullTest();
            joinDistinctTest();
            joinInnerTest();
            joinOuterTest();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        catch (Exception e) {
            result = false;
            System.out.println("Unhandled exception: " + e.toString());
        }
        finally {
            if (result)
                System.out.println("All tested passed successfully");
        }
    }

    private static void prepareData() {
        persons1 = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        persons2 = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };

    }

}

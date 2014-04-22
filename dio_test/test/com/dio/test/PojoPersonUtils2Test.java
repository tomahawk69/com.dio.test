package com.dio.test;

import java.util.Arrays;

public class PojoPersonUtils2Test extends BaseTest {
    private static PojoPersonUtils2 tester;
    private static PojoPerson[] persons1, persons2;

    public static void setUp() {
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

    public static boolean testJoinFull() throws TestNotPassedException {
        setUp();
        PojoPerson[] resultExpected = new PojoPerson[]{
                persons1[0],
                persons1[1],
                persons2[0],
                persons2[1],
                persons2[2],
                persons2[3]
        };
        PojoPerson[] resultReceived = tester.joinFull(persons1, persons2);
        if (!Arrays.equals(resultExpected, resultReceived)) {
            errorMessageArr("testJoinFull", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testJoinDistinct() throws TestNotPassedException {
        setUp();
        PojoPerson[] resultExpected = new PojoPerson[]{
                persons1[0],
                persons1[1],
                persons2[0],
                persons2[1],
                persons2[2]
        };
        PojoPerson[] resultReceived = tester.joinDistinct(persons1, persons2);
        if (!Arrays.equals(resultExpected, resultReceived)) {
            errorMessageArr("testJoinDistinct 1", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testJoinDistinctReverse() throws TestNotPassedException {
        setUp();
        PojoPerson[] resultExpected = new PojoPerson[]{
                persons2[0],
                persons2[1],
                persons2[2],
                persons2[3],
                persons1[0]
        };
        PojoPerson[] resultReceived = tester.joinDistinct(persons2, persons1);
        if (!Arrays.equals(resultExpected, resultReceived)) {
            errorMessageArr("testJoinDistinct Reverse", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testJoinInner() throws TestNotPassedException {
        setUp();
        PojoPerson[] resultExpected = new PojoPerson[]{
                persons1[1],
        };
        PojoPerson[] resultReceived = tester.joinInner(persons1, persons2);
        if (!Arrays.equals(resultExpected, resultReceived)) {
            errorMessageArr("testJoinInner", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testJoinInnerNull() throws TestNotPassedException {
        setUp();
        PojoPerson[] resultExpected = new PojoPerson[]{
        };
        PojoPerson[] resultReceived = tester.joinInner(null, persons2);
        if (!Arrays.equals(resultExpected, resultReceived)) {
            errorMessageArr("testJoinInner Null", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testJoinOuter() throws TestNotPassedException {
        setUp();
        PojoPerson[] resultExpected = new PojoPerson[]{
                persons1[0],
                persons2[0],
                persons2[1],
                persons2[2],
        };
        PojoPerson[] resultReceived = tester.joinOuter(persons1, persons2);
        if (!Arrays.equals(resultExpected, resultReceived)) {
            errorMessageArr("testJoinOuter", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testContainsPositive() throws TestNotPassedException {
        setUp();
        boolean resultExpected = true;
        boolean resultReceived = tester.contains(persons1, persons1[1]);
        if (resultExpected != resultReceived) {
            errorMessage("testContainsPositive", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testContainsNegative() throws TestNotPassedException {
        setUp();
        boolean resultExpected = false;
        boolean resultReceived = tester.contains(persons1, persons2[1]);
        if (resultExpected != resultReceived) {
            errorMessage("testContainsNegative", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public static boolean testContainsException() throws TestNotPassedException {
        setUp();
        boolean result = false;
        try {
            tester.contains(persons1, null);
        }
        catch (IllegalArgumentException e) {
            result = true;
        }
        finally {
            if (!result)
                errorMessage("testContainsException", "exception", "no exception");
            return result;
        }
    }

    public static void main(String[] args) {
        tester = new PojoPersonUtils2();
        boolean result = true;

        try {
            testJoinFull();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testJoinDistinct();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testJoinDistinctReverse();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testJoinInner();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testJoinInnerNull();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testJoinOuter();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testContainsPositive();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testContainsNegative();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            testContainsException();
        }
        catch (TestNotPassedException e) {
            result = false;
        }

        if (result)
            System.out.println("All tests passed successful");
    }
}
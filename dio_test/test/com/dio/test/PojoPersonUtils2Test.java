package com.dio.test;

import java.util.Arrays;

public class PojoPersonUtils2Test extends BaseTest {
    private PojoPersonUtils2 tester;
    private PojoPerson[] persons1, persons2;

    public void setUp() {
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

    public boolean testJoinFull() throws TestNotPassedException {
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

    public boolean testJoinDistinct() throws TestNotPassedException {
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

    public boolean testJoinDistinctReverse() throws TestNotPassedException {
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

    public boolean testJoinInner() throws TestNotPassedException {
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

    public boolean testJoinInnerNull() throws TestNotPassedException {
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

    public boolean testJoinOuter() throws TestNotPassedException {
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

    public boolean testContainsPositive() throws TestNotPassedException {
        setUp();
        boolean resultExpected = true;
        boolean resultReceived = tester.contains(persons1, persons1[1]);
        if (resultExpected != resultReceived) {
            errorMessage("testContainsPositive", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public boolean testContainsNegative() throws TestNotPassedException {
        setUp();
        boolean resultExpected = false;
        boolean resultReceived = tester.contains(persons1, persons2[1]);
        if (resultExpected != resultReceived) {
            errorMessage("testContainsNegative", resultExpected, resultReceived);
            return false;
        }
        return true;
    }

    public boolean testContainsException() throws TestNotPassedException {
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
        boolean result = true;
        PojoPersonUtils2Test test = new PojoPersonUtils2Test();

        try {
            test.testJoinFull();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testJoinDistinct();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testJoinDistinctReverse();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testJoinInner();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testJoinInnerNull();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testJoinOuter();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testContainsPositive();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testContainsNegative();
        }
        catch (TestNotPassedException e) {
            result = false;
        }
        try {
            test.testContainsException();
        }
        catch (TestNotPassedException e) {
            result = false;
        }

        if (result)
            System.out.println("All tests passed successful");
    }
}
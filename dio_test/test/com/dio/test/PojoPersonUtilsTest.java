package com.dio.test;
import com.dio.test.PojoPerson;
import com.dio.test.PojoPersonUtils;
import com.dio.test.EnumJob;

import java.util.Arrays;


/**
 * Created by yur on 21.04.2014.
 */
public class PojoPersonUtilsTest  extends BaseTest {

    private static PojoPerson[] arr1, arr2;

    private static boolean joinFullTest() throws TestNotPassedException {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinFull(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinFullTest test 1", re, rr);
            return false;
        }

        rr = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        re = PojoPersonUtils.joinFull(arr2, arr1);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinFullTest test 2", re, rr);
            return false;
        }
        return true;
    }

    private static boolean joinDistinctTest() throws TestNotPassedException {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinDistinct(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinDistinctTest test 1", re, rr);
            return false;
        }

        rr = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last"),
        };
        re = PojoPersonUtils.joinDistinct(arr2, arr1);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinDistinctTest test 2", re, rr);
            return false;
        }
        return true;
    }

    private static boolean joinInnerTest() throws TestNotPassedException {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinInner(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinInnerTest test 1", re, rr);
            return false;
        }

        re = PojoPersonUtils.joinInner(arr2, arr1);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinInnerTest test 2", re, rr);
            return false;
        }
        return true;
    }

    private static boolean joinOuterTest() throws TestNotPassedException {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinOuter(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinOuterTest test 1", re, rr);
            return false;
        }

        rr = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last"),
        };
        re = PojoPersonUtils.joinOuter(arr2, arr1);

        if (!Arrays.equals(re, rr)) {
            errorMessageArr("joinOuterTest test 2", re, rr);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        prepareData();

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
        arr1 = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        arr2 = new PojoPerson[]{
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };

    }

}

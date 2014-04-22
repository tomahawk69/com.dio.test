package com.dio.test;
import java.util.Arrays;

/**
 * Base class for testing
 * Created by iovchynnikov on 4/18/14.
 */
public class BaseTest {
    protected static void errorMessage(String message, String resultExpected, String resultReceived) throws TestNotPassedException {
        System.out.println(message + " does not passed");
        System.out.println("Expected: " + resultExpected);
        System.out.println("Received: " + resultReceived);
        throw new TestNotPassedException(message + " does not passed");
    }
    protected static void errorMessage(String message, boolean resultExpected, boolean resultReceived) throws TestNotPassedException {
        errorMessage(message, resultExpected, resultReceived);
    }
    protected static void errorMessageArr(String message, Object[] resultExpected, Object[] resultReceived) throws TestNotPassedException {
        errorMessage(message, Arrays.toString(resultExpected), Arrays.toString(resultReceived));
    }


    protected static void validMessage(String s) {
        System.out.println(s + " passed!");
    }
    /**
     * Exception with class
     * @param s
     * @param re
     * @param rr
     * @param ex
     */
    /*
    protected static void errorMessage(String s, String re, String rr, Exception ex) throws TestNotPassedException  {
        errorMessage(s, re, rr);
        throw new TestNotPassedException(s + " does not passed");
    }
    */

}


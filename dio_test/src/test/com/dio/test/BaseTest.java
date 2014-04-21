package test.com.dio.test;
import java.util.Arrays;

/**
 * Base class for testing
 * Created by iovchynnikov on 4/18/14.
 */
public class BaseTest {
    protected static void errorMessage(String s, String re, String rr) throws TestNotPassedException {
        System.out.println(s + " does not passed");
        System.out.println("Expected: " + re);
        System.out.println("Received: " + rr);
        throw new TestNotPassedException(s + " does not passed");
    }
    protected static void errorMessageArr(String s, Object[] re, Object[] rr) throws TestNotPassedException {
        errorMessage(s, Arrays.toString(re), Arrays.toString(rr));
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


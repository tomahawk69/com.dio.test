package test.com.dio.test;

import com.dio.test.ArrayAdv;

import java.util.Arrays;

/**
 * Created by iovchynnikov on 4/17/14.
 * Test class for com.dio.test.ArrayAdv
 */
public class ArrayAdvTest extends BaseTest {
    private static String[] a1, a2;

    /**
     * testing method for full arrays join
     */
    public static boolean testJoinArrFull() {
        String[] rr, re;
        Boolean result = true;

        // test full join
        rr = ArrayAdv.joinArrFull(a1, a2);
        re = new String[]{"Second", "First", "Last", "Zero", "First", "Third", "zero", "A"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrFull test 1", re, rr);
            result = false;
        }
        rr = ArrayAdv.joinArrFull(a2, a1);
        re = new String[]{"First", "Third", "zero", "A", "Second", "First", "Last", "Zero"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrFull test 2", re, rr);
            result = false;
        }
        return result;
    }

    public static boolean testJoinArrDistinct() {
        String[] rr, re;
        Boolean result = true;

        // test distinct join
        rr = ArrayAdv.joinArrDistinct(a1, a2);
        re = new String[]{"First", "Last", "Second", "Zero", "A", "Third"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrDistinct test 1", re, rr);
            result = false;
        }
        rr = ArrayAdv.joinArrDistinct(a2, a1);
        re = new String[]{"A", "First", "Third", "zero", "Last", "Second"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrDistinct test 2", re, rr);
            result = false;
        }
        return result;
    }

    public static boolean testJoinArrInner() {
        String[] rr, re;
        Boolean result = true;

        // test distinct join
        rr = ArrayAdv.joinArrInner(a1, a2);
        re = new String[]{"First", "Zero"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrInner test 1", re, rr);
            result = false;
        }
        rr = ArrayAdv.joinArrInner(a2, a1);
        re = new String[]{"First", "zero"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrInner test 2", re, rr);
            result = false;
        }
        return result;
    }

    public static boolean testRemoveDupes() {
        String[] rr, re;
        Boolean result = true;
        // test distinct join
        rr = ArrayAdv.removeDupes(ArrayAdv.joinArrFull(a1, a2));
        re = new String[]{"A", "First", "Last", "Second", "Third", "Zero", "zero"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testRemoveDupes test 1", re, rr);
            result = false;
        }
        return result;
    }

    private static boolean testRemoveDupesIgnoreCase() {
        String[] rr, re;
        Boolean result = true;
        // test distinct join
        rr = ArrayAdv.removeDupesIgnoreCase(ArrayAdv.joinArrFull(a1, a2));
        re = new String[]{"A", "First", "Last", "Second", "Third", "Zero"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testRemoveDupesIgnoreCase test 1", re, rr);
            result = false;
        }
        return result;
    }


    public static boolean testJoinArrOuter() {
        String[] rr, re;
        Boolean result = true;

        // test outer join
        rr = ArrayAdv.joinArrOuter(a1, a2);
        re = new String[]{"Last", "Second", "A", "Third"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrInner test 1", re, rr);
            result = false;
        }
        rr = ArrayAdv.joinArrOuter(a2, a1);
        re = new String[]{"A", "Third", "Last", "Second"};
        if (!Arrays.equals(rr, re)) {
            errorMessage("testJoinArrInner test 2", re, rr);
            result = false;
        }
        return result;
    }

    /**
     * Common error message for array testing
     * @param s test name
     * @param re expected array
     * @param rr result array
     */
    private static void errorMessage(String s, String[] re, String[] rr) {
        errorMessage(s, Arrays.toString(re), Arrays.toString(rr));
    }

    public static void main(String[] args) {
        // prepare data
        prepareData();

        boolean result =  testJoinArrFull();
        if (!testJoinArrDistinct())
            result = false;
        if (!testJoinArrInner())
            result = false;
        if (!testJoinArrOuter())
            result = false;
        if (!testRemoveDupes())
            result = false;
        if (!testRemoveDupesIgnoreCase())
            result = false;

        if (result)
            System.out.println("All tested passed successfully");
    }

    /**
     * recreate input data
     */
    private static void prepareData() {
        a1 = new String[]{"Second", "First", "Last", "Zero"};
        a2 = new String[]{"First", "Third", "zero", "A"};
    }
}

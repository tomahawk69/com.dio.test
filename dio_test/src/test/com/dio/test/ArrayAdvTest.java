package test.com.dio.test;

import com.dio.test.ArrayAdv;

import java.util.Arrays;

/**
 * Created by iovchynnikov on 4/17/14.
 * Test class for com.dio.test.ArrayAdv
 */
public class ArrayAdvTest {
    public static void testJoinArrFull() {
        String[] a1, a2, rr, re;
        a1 = new String[]{"Second", "First", "Last"};
        a2 = new String[]{"First", "Third", "Zero"};

        // test join
        rr = ArrayAdv.joinArrFull(a1, a2);
        re = new String[]{"Second", "First", "Last", "First", "Third", "Zero"};
        if (!Arrays.equals(rr, re)) {
            System.out.println("Test 1 does not passed");
            System.out.println("Exprected: " + Arrays.toString(re));
            System.out.println("Received: " + Arrays.toString(rr));
        }
    }

    public static void main(String[] args) {
        // full join test
        testJoinArrFull();
    }
}

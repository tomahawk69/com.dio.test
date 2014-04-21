package test.com.dio.test;
import com.dio.test.PojoPerson;
import com.dio.test.PojoPersonUtils;
import com.dio.test.EnumJob;

import java.util.Arrays;


/**
 * Created by yur on 21.04.2014.
 */
public class PojoPersonUtilsTest  extends BaseTest {

    private static PojoPerson[] arr1, arr2;

    private static boolean joinFullTest() {
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
            errorMessage("joinFullTest test 1", re, rr);
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
            errorMessage("joinFullTest test 2", re, rr);
            return false;
        }
        return true;
    }

    private static boolean joinDistinctTest() {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinDistinct(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessage("joinDistinctTest test 1", re, rr);
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
            errorMessage("joinDistinctTest test 2", re, rr);
            return false;
        }
        return true;
    }

    private static boolean joinInnerTest() {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinInner(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessage("joinInnerTest test 1", re, rr);
            return false;
        }

        re = PojoPersonUtils.joinInner(arr2, arr1);

        if (!Arrays.equals(re, rr)) {
            errorMessage("joinInnerTest test 2", re, rr);
            return false;
        }
        return true;
    }

    private static boolean joinOuterTest() {
        PojoPerson[] rr = new PojoPerson[]{
                new PojoPerson("First", "Second", "Last"),
                new PojoPerson("First", null, "Last"),
                new PojoPerson("First", "Second", "Last", EnumJob.DIRECTOR),
                new PojoPerson("FIRSTt", "Second", "Last", EnumJob.DEVELOPER),
        };
        PojoPerson[] re = PojoPersonUtils.joinOuter(arr1, arr2);

        if (!Arrays.equals(re, rr)) {
            errorMessage("joinOuterTest test 1", re, rr);
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
            errorMessage("joinOuterTest test 2", re, rr);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        prepareData();

        boolean result = joinFullTest();
        if (!joinDistinctTest())
            result = false;
        if (!joinInnerTest())
            result = false;
        if (!joinOuterTest())
            result = false;

        if (result)
            System.out.println("All tested passed successfully");
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
    private static void errorMessage(String s, Object[] re, Object[] rr) {
        errorMessage(s, Arrays.toString(re), Arrays.toString(rr));
    }

}

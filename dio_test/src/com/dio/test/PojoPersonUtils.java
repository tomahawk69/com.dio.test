package com.dio.test;

/**
 * Created by yur on 21.04.2014.
 * Util class for PojoPerson
 */
public class PojoPersonUtils {

    /**
     * Full join
     * Considerate nulls in parameters
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public static PojoPerson[] joinFull(PojoPerson[] arr1, PojoPerson[] arr2) {
        PojoPerson[] result = new PojoPerson[(arr1 == null ? 0 : arr1.length) +
                (arr2 == null ? 0 : arr2.length)];
        if (arr1 != null)
            System.arraycopy(arr1, 0, result, 0, arr1.length);
        if (arr2 != null)
            System.arraycopy(arr2, 0, result, (arr1 == null ? 0 : arr1.length), arr2.length);
        return result;
    }

    /**
     * Distinct join
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public static PojoPerson[] joinDistinct(PojoPerson[] arr1, PojoPerson[] arr2) {
        PojoPerson[] temp = new PojoPerson[arr1.length + arr2.length];
        int i = 0;
        // scan first array
        for (PojoPerson s : arr1)
            if (!contains(temp, s))
                temp[i++] = s;
        // scan second array
        for (PojoPerson s : arr2)
            if (!contains(temp, s))
                temp[i++] = s;

        PojoPerson[] result = new PojoPerson[i];
        System.arraycopy(temp, 0, result, 0, i);

        return result;
    }

    /**
     * Inner join
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public static PojoPerson[] joinInner(PojoPerson[] arr1, PojoPerson[] arr2) {
        PojoPerson[] temp = new PojoPerson[arr1.length + arr2.length];
        int i = 0;
        // pass only first array
        for (PojoPerson s : arr1)
            if (contains(arr2, s) && !contains(temp, s))
                temp[i++] = s;

        PojoPerson[] result = new PojoPerson[i];
        System.arraycopy(temp, 0, result, 0, i);

        return result;
    }

    /**
     * Outer join
     * @param arr1 PojoPerson 1
     * @param arr2 PojoPerson 2
     * @return PojoPerson result
     */
    public static PojoPerson[] joinOuter(PojoPerson[] arr1, PojoPerson[] arr2) {
        PojoPerson[] temp = new PojoPerson[arr1.length + arr2.length];
        int i = 0;
        // initial sorting of two arrays
        for (PojoPerson s : arr1)
            if (!contains(arr2, s) && !contains(temp, s))
                temp[i++] = s;
        // scan second array
        for (PojoPerson s : arr2)
            if (!contains(arr1, s) && !contains(temp, s))
                temp[i++] = s;

        PojoPerson[] result = new PojoPerson[i];
        System.arraycopy(temp, 0, result, 0, i);

        return result;
    }

    public static boolean contains(PojoPerson[] arr, PojoPerson s) {
        if (arr == null)
            return false;

        for (PojoPerson item : arr) {
            if (item != null && item.getClass().getSimpleName().equals("PojoPerson") && item.equals(s)) {
                return true;
            }
        }
        return false;
    }

}

package com.dio.test;

/**
 * Created by yur on 13.04.2014.
 * Advanced operations with array
 */
public class ArrayAdv {

    /**
     * FULL join odf arrays
     * without duplicates check
     * @param arr1 first array
     * @param arr2 second array
     * @return resulting array
     */
    public static  String[] joinArrFull(String[] arr1, String[] arr2) {
        String[] result = new String[arr1.length + arr2.length];
        // copy first array
        System.arraycopy(arr1, 0, result, 0, arr1.length);
        // copy second array
        // we do not check if it is the same arrays as first
        System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
        return result;
    }



    /*
    * Join two arrays in a very straightforward way
    * Joins can be:
    * <ol>
    * <li>FULL - include all elements from both arrays
    * <LI>DISTINCT - include all distinct values from both arrays
    * <li>INNER - include all distinct values that exist in both arrays
    * <li>OUTER - include all distinct values that exist only in first or second array
    * </ol>
    * @param  arr1, arr2  not null arrays of strings
    * @param  mergeType  type of join
    * @return resulting array of strings
    */
    public static String[] joinArr(String[] arr1, String[] arr2, arrMergeType mergeType) {
        String[] result = new String[arr1.length + arr2.length];
        int i = 0;

        boolean allow;
        for (String s : arr1) {
            switch (mergeType) {
                case FULL:
                case DISTINCT:
                    allow = true;
                    break;
                case INNER:
                    allow = arrContains(arr2, s);
                    break;
                case OUTER:
                    allow = !arrContains(arr2, s);
                    break;
                default:
                    allow = false;
                    break;
            }
            if (allow && (mergeType == arrMergeType.FULL || !arrContains(result, s)))
                result[i++] = s;
        }

        if (mergeType != arrMergeType.INNER)
            for (String s : arr2) {
                switch (mergeType) {
                    case FULL:
                    case DISTINCT:
                        allow = true;
                        break;
                    case OUTER:
                        allow = !arrContains(arr1, s);
                        break;
                    default:
                        allow = false;
                }
                if (allow && (mergeType == arrMergeType.FULL || !arrContains(result, s)))
                    result[i++] = s;
            }
        String[] realResult = new String[i];
        System.arraycopy(result, 0, realResult, 0, i);
        return realResult;
    }

    /*
    * Check if not-null array of strings contains specified string value.
    * There is small tune -
    * @param  arr not null array of strings
    * @param  search  search value
    * @return true if value found or false otherwise
    */
    public static boolean arrContains(String[] arr, String search) {
        if (arr != null)
            for (String s : arr) {
                if (s == null) break;
                if (search.equalsIgnoreCase(s))
                    return true;
                if (search.compareTo(s) < 0)
                    break;
            }
        return false;
    }

    /**
     * Bubble method sort
     * @param arr source array
     * @return sorted array
     */
    public static String[] sort(String[] arr) {
        int i = 1;
        int j;
        String temp;
        while (i < arr.length) {
            if (arr[i].compareToIgnoreCase(arr[i-1]) < 0) {
                temp = arr[i];
                arr[i] = arr[i-1];
                arr[i-1] = temp;
                if (i > 1) i--;
            } else i++;
        }
        return arr;
    }

}

enum arrMergeType {
    FULL, DISTINCT, INNER, OUTER
}

package com.dio.test;

/**
 * Created by yur on 13.04.2014.
 * Advanced array utilities
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

    /**
     * DISTINCT join of two arrays
     * @param arr1
     * @param arr2
     * @return resulting array
     */
    public static  String[] joinArrDistinct(String[] arr1, String[] arr2) {
        String[] temp = new String[arr1.length + arr2.length];
        int i = 0;
        // initial sorting of two arrays
        String[] a1 = sort(arr1), a2 = sort(arr2);
        // scan first array
        for (String s : a1)
            if (!arrContains(temp, s))
                temp[i++] = s;
        // scan second array
        for (String s : a2)
            if (!arrContains(temp, s))
                temp[i++] = s;

        String[] result = new String[i];
        System.arraycopy(temp, 0, result, 0, i);

        return result;
    }

    /**
     * INNER join of two arrays
     * @param arr1
     * @param arr2
     * @return resulting array
     */
    public static  String[] joinArrInner(String[] arr1, String[] arr2) {
        String[] temp = new String[arr1.length + arr2.length];
        int i = 0;
        // initial sorting of two arrays
        String[] a1 = sort(arr1), a2 = sort(arr2);
        // pass only first array
        for (String s : a1)
            if (arrContains(a2, s) && !arrContains(temp, s))
                temp[i++] = s;

        String[] result = new String[i];
        System.arraycopy(temp, 0, result, 0, i);

        return result;
    }

    /**
     * OUTER join of two arrays
     * @param arr1
     * @param arr2
     * @return resulting array
     */
    public static  String[] joinArrOuter(String[] arr1, String[] arr2) {
        String[] temp = new String[arr1.length + arr2.length];
        int i = 0;
        // initial sorting of two arrays
        String[] a1 = sort(arr1), a2 = sort(arr2);
        // scan first array
        for (String s : a1)
            if (!arrContains(a2, s) && !arrContains(temp, s))
                temp[i++] = s;
        // scan second array
        for (String s : a2)
            if (!arrContains(a1, s) && !arrContains(temp, s))
                temp[i++] = s;

        String[] result = new String[i];
        System.arraycopy(temp, 0, result, 0, i);

        return result;
    }


    /*
    * Check if not-null array of strings contains specified string value.
    * Arrays should be sorted
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
     * Remove dupes from array case-sensitive
     * @param arr input array
     * @return result sorted array
     */
    public static String[] removeDupes(String[] arr) {
        String[] temp = new String[arr.length];
        String value = "";
        int i = 0;
        for (String item : sort(arr) )
            if (i == 0 || !value.equals(item)) {
                temp[i++] = item;
                value = item;
            }
        String[] result = new String[i];
        System.arraycopy(temp, 0, result, 0, i);
        return result;
    }

    /**
     * Remove dupes from array case-insensitive
     * @param arr input array
     * @return result sorted array
     */
    public static String[] removeDupesIgnoreCase(String[] arr) {
        String[] temp = new String[arr.length];
        String value = "";
        int i = 0;
        for (String item : sort(arr) )
            if (i == 0 || !value.equalsIgnoreCase(item)) {
                temp[i++] = item;
                value = item;
            }
        String[] result = new String[i];
        System.arraycopy(temp, 0, result, 0, i);
        return result;
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
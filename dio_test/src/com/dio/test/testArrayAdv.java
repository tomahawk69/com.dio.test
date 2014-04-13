/**
 * Created by yur on 13.04.2014.
 * Testing unit for ArrayAdv utility
 */
package com.dio.test;

public class testArrayAdv {
    public static void main(String[] args) {

        String[] a1 = {"Second", "First", "Last"};
        String[] a2 = {"First", "Third", "Zero"};

        System.out.println("Full merge type:");
        testArrayAdv.printArr(ArrayAdv.joinArr(a1, a2, arrMergeType.FULL));

        System.out.println("Distinct merge type:");
        printArr(ArrayAdv.joinArr(a1, a2, arrMergeType.DISTINCT));

        System.out.println("Inner merge type:");
        printArr(ArrayAdv.joinArr(a1, a2, arrMergeType.INNER));

        System.out.println("Outer merge type:");
        printArr(ArrayAdv.joinArr(a1, a2, arrMergeType.OUTER));

        System.out.println("Sort distinct array:");
        printArr(ArrayAdv.sort(ArrayAdv.joinArr(a1, a2, arrMergeType.DISTINCT)));
    }


    /*
    * Print array of strings as set of items delimited by space
    * @param  arr not null array of strings
    */
    public static void printArr (String[] arr) {
        for (String s: arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

}

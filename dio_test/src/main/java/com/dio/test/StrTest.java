/**
 * Created by yur on 07.04.2014.
 * Test string comparison
 */

package com.dio.test;


public class StrTest {
    public static void main(String[] args) {
        String str1 = "string";
        String str2 = new String("string"); // redundant
        System.out.println(str1.intern());
        System.out.println(str1.intern() == str2.intern() ? "the same" : "not the same"); // comparing interns
    }
}

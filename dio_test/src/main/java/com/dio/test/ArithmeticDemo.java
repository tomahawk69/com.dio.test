package com.dio.test;

/**
 * Created by iovchynnikov on 4/9/14.
 * Testing simple operators
 */
public class ArithmeticDemo {

        public static void main (String[] args){

            int result = 1 + 2; // result is now 3
            System.out.println(result);

            --result; // result is now 2
            System.out.println(result);

            result *= 2; // result is now 4
            System.out.println(result);

            result /= 2; // result is now 2
            System.out.println(result);

            result +=  8; // result is now 10
            result %= 7; // result is now 3
            System.out.println(result);
        }
    }

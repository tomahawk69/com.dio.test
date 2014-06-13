package com.dio.test;
/**
 * Created by iovchynnikov on 4/9/14.
 * Testing bitwise operations
 */
public class BitwiseTest {
    public static void main(String[] args) {
        int bitmask = 0x000F;
        int val = 0x2222;
        // prints "2"
        System.out.println(val & bitmask);

        val = 5; // 101
        bitmask = 4; // 100
        System.out.println(val + " & " + bitmask + " = " + (val & bitmask));
        if ((val & bitmask) == bitmask) System.out.println("Agreed"); else System.out.println("Disagreed");

        val = 2 + 4; // 110
        bitmask = 1; // 001
        System.out.println(val + " & " + bitmask + " = " + (val & bitmask));
        System.out.println(val + " ^ " + bitmask + " = " + (val ^ bitmask));
        System.out.println(val + " | " + bitmask + " = " + (val | bitmask));
        if ((val & bitmask) == bitmask) System.out.println("Agreed"); else System.out.println("Disagreed");

        System.out.println("~ of " + val + " is " + (~val));
        System.out.println(">> 2 of " + val + " is " + (val >> 2)); // shift to 2 position to the left
        System.out.println("<< 1 of " + val + " is " + (val << 1)); // shift to 1 position to the right

        //byte bval = 0b0010;
        //byte bbitmask = 0b0110;



    }
}



package com.dio.test;

/**
 * Created by yur on 13.04.2014.
 * Simple unit for creating, accessing arrays
 */
public class ArrayTest {
    public static void main(String[] srgs) {
        String[] strArr = new String[]{"zero", "first", "second"};
        for (String itemArr: strArr) {
            System.out.println(itemArr.toUpperCase());
        }
        myArrEnum[] enumArr = myArrEnum.values();
        for (myArrEnum itemArr: enumArr) {
            System.out.println(itemArr);
        }
        char[][] imgData =
                new char[][] {
                        { ' ',' ',' ',' ',' ',' ',' ' },
                        { ' ',' ',' ','0',' ',' ',' ' },
                        { ' ',' ',' ','|',' ',' ',' ' },
                        { ' ','0','-','+','-','0',' ' },
                        { ' ',' ',' ','|',' ',' ',' ' },
                        { ' ',' ',' ','0',' ',' ',' ' },
                        { ' ',' ',' ',' ',' ',' ',' ' }
                };

        for (int row = 0; row < imgData.length ; row++ ) {
            for (int col = 0; col < imgData[row].length; col++ ) {
                System.out.print(imgData[row][col]);
            }
            System.out.println();
        }

    }
}

enum myArrEnum {
    ZERO, FIRST, SECOND
}

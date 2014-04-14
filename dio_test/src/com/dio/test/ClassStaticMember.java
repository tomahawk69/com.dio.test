package com.dio.test;
/**
 * Created by iovchynnikov on 4/8/14.
 * Static class member test
 */
public class ClassStaticMember {
    public static int staticField;

    public static void main(String[] args) {
        ClassStaticMember o1 = new ClassStaticMember();
        ClassStaticMember o2 = new ClassStaticMember();
        ClassStaticMember o3 = o1; // redundant

        System.out.println("First stage");
        o1.printInfo();
        o2.printInfo();
        o3.printInfo();

        o2.staticField = 2; // much appropriate method to reach static (class) variable
        System.out.println("Second stage");
        o1.printInfo();
        o2.printInfo();
        o3.printInfo();

        ClassStaticMember.staticField = 4;
        System.out.println("Third stage");
        o1.printInfo();
        o2.printInfo();
        o3.printInfo();


    }

    public void printInfo() {
        System.out.println("Static field value is " + staticField);
    }


}

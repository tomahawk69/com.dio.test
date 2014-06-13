package com.dio.test.classes;

/**
 * Created by iovchynnikov on 4/15/14.
 */
public class AbstractClassInh extends AbstractClass {
    public static void main(String[] args) {
        AbstractClassInh obj = new AbstractClassInh();
        System.out.println(obj);
    }

    @Override
    public String asString() {
        return null;
    }
}

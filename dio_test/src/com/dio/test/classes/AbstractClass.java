package com.dio.test.classes;

/**
 * Created by iovchynnikov on 4/15/14.
 */
abstract public class AbstractClass {
    private String name;
    public void AbstractClass() {
        name = "AbstractClass";
    }
    public void AbstractClass(String pname) {
        name = pname;
    }

    public String asString() {
        return name;
    }

}

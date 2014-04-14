/**
 * Created by yur on 07.04.2014.
 * Very simple interface
 * With Java 8 feature - default method
 */

package com.dio.test;

public interface TestInterface {
    public void printStatus();
    public void copyStatus(TestInterface source);
    public int getStatus();
    public void setStatus(int newStatus);
    /*
    static void testDefault() {
        System.out.println("Default method");
    }

    default int testDef() {
        testDefault();
        return 0;
    }
    */
    public enum testEnum {
        INIT,
        FIRST,
        SECOND,
        THIRD,
        FOURTH
    }
}

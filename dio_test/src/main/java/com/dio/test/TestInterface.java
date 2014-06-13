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
        INIT(0),
        FIRST(1),
        SECOND(2),
        THIRD(3),
        FOURTH(4);
        private final int state;
        testEnum(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
        public static testEnum typeOf(int state) {
            for (testEnum value : testEnum.values()) {
                if (value.getState() == state)
                    return value;
            }
            return null;
        }
    }
}

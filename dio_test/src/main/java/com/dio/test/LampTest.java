/**
 * Created by yur on 06.04.2014.
 * Test unit for the Lamp class
 */

package com.dio.test;


public class LampTest {

    public static void main(String[] args) {
        Lamp l1 = new Lamp();
        l1.state();
        l1.turnOn();
        l1.state();
        l1.turnOff();
        l1.state();
    }
}

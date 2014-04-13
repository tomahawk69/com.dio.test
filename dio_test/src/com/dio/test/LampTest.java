/**
 * Created by yur on 06.04.2014.
 * Test unit for the Lamp class
 */

package com.dio.test;


public class LampTest {

    public static void main(String[] args) {
        Lamp l1 = new Lamp();
        l1.State();
        l1.TurnOn();
        l1.State();
        l1.TurnOff();
        l1.State();
    }
}

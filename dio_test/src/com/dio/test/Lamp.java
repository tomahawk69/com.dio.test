/**
 * Created by yur on 06.04.2014.
 * Implemetation of POJO class Lamp
 * Lamp has switch off, switch on, and status methods
 */
package com.dio.test;

public class Lamp {
    private int state;

    {
        this.state = 0;
    }

    public void TurnOn() {
        this.ChangeState(1);
    }

    public void TurnOff() {
        this.ChangeState(0);
    }

    private void ChangeState(int newstate) {
        this.state = newstate;
    }

    public void State() {
        System.out.print("System is ");
        if (this.state == 1) {
            System.out.println("up");
        } else {
            System.out.println("down");
        }

    }
}


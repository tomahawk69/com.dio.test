/**
 * Created by yur on 07.04.2014.
 * Implementation of simple interface
 * Class has private member status, setter, getter, print status method and constructor
 * Used enum for checking enum methods
 */

package com.dio.test;


public class ClassWithInterface implements TestInterface {
    private int sstatus;
    private testEnum estatus;

    /**
     * Test procedure for ClassWithInterface
     *
     * @param args as usually
     */
    public static void main(String[] args) {
        ClassWithInterface o1 = new ClassWithInterface();
        o1.printStatus();
        o1.setStatus(2);
        o1.printStatus();

        ClassWithInterface o2 = new ClassWithInterface();
        o2.printStatus();
        o2.copyStatus(o1);
        o2.printStatus();

        o1.setStatus(3);
        o1.printStatus();
        o2.copyStatus(null);
        o2.printStatus();

        o1.testDef();
    }

    /**
     * Constructor
     * set status
     */
    public ClassWithInterface() {
        this.sstatus = 0;
        this.estatus = testEnum.INIT;
    }


    @Override
    public int testDef() {
        System.out.println("Overrided");
        return 0;
    }

    @Override
    public void printStatus() {
        System.out.println("Status is " + sstatus + " (" + this.estatus + ")");
    }

    /**
     * Copy status from one object to other
     * @param source source object
     */
    @Override
    public void copyStatus(TestInterface source) {
        if (source instanceof ClassWithInterface) {
            this.sstatus = ((ClassWithInterface) source).getStatus(); // redundant, only for casting training
            this.estatus = testEnum.values()[this.sstatus];
        }
    }

    /**
     * Simple getter
     * @return  int value of the status
     */
    @Override
    public int getStatus() {
        return sstatus;
    }

    /**
     * Setter
     * Set int and enum value of  the status
     * @param newStatus new status
     */
    @Override
    public void setStatus(int newStatus) {
        this.sstatus = newStatus;
        this.estatus = testEnum.values()[this.sstatus];
    }
}

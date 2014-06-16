package com.dio.aifmd.interfaceinh;

import java.rmi.RemoteException;

/**
 * Created by yur on 17.06.2014.
 */
public class TestInterfaceMain {

    public static void main(String[] args) {
        TestInterface test1 = new TestInterfaceImpl();

        try {
            test1.testSample();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        TestInterface test2 = new TestInterfaceImpl2();
        try {
            test2.testSample();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        TestInterfaceChild1 test3 = new TestInterfaceChild1Impl();
        test3.testSample();
    }
}

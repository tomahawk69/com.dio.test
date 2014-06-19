package com.dio.aifmd.interfaceinh;

import java.rmi.RemoteException;

/**
 * Created by yur on 17.06.2014.
 */
public class TestInterfaceMain {

    public static void main(String[] args) {
        TestInterfaceRemote test1 = new TestInterfaceRemoteImpl();

//        try {
//            test1.testSample();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        TestInterfaceRemote test2 = new TestInterfaceRemoteImpl2();
//        try {
//            test2.testSample();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        TestInterfaceSimple test3 = new TestInterfaceSimpleImpl();
//        test3.testSample();
    }
}

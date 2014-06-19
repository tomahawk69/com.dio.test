package com.dio.aifmd.interfaceinh;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by yur on 17.06.2014.
 */
public class TestInterfaceWraperImpl implements TestInterfaceSimple {

    private final TestInterfaceRemote testInterfaceRemote;

    public TestInterfaceWraperImpl(TestInterfaceRemote testInterfaceRemote) {
        this.testInterfaceRemote = testInterfaceRemote;
    }

    @Override
    public void testSample() throws IOException {

        try {
            testInterfaceRemote.testSample();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

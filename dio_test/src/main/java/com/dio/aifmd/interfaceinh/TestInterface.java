package com.dio.aifmd.interfaceinh;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by yur on 17.06.2014.
 */
public interface TestInterface extends Remote {
    public void testSample() throws RemoteException;


}

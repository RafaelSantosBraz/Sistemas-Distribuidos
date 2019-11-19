/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Fábio Moreno
 */
public interface Server extends Remote {

    public String getDataHora() throws RemoteException;

    public String invertString(String string) throws RemoteException;

}

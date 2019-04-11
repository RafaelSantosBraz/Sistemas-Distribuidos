/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Fábio Moreno
 */
public class ServerImpl extends UnicastRemoteObject implements Server {

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String getDataHora() throws RemoteException {
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String Data = sdf.format(Calendar.getInstance().getTime()) + " servidor 1";
        return Data;

    }

    @Override
    public String invertString(String string) throws RemoteException {
        StringBuilder str = new StringBuilder(string);
        String retorno = str.reverse().toString();
        return retorno;
    }
}

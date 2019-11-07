/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Node;

/**
 *
 * @author Rafael Braz
 */
public interface Service extends Remote {

    public void receiveRequest(String fileName, Node sourceNode) throws RemoteException;

    public void startRequest(String fileName) throws RemoteException;

    public boolean isFileAlreadyThere(String fileName) throws RemoteException;

    public void receiveFile(String fileName, byte[] content) throws RemoteException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Rafael Braz
 */
public class P2PService extends UnicastRemoteObject implements Service {
    
    private final FileControl fileControl;
    private NodeControl nodeControl;
    
    public P2PService(String dirPath) throws RemoteException {
        super();
        fileControl = new FileControl(dirPath);
        nodeControl = new NodeControl();
    }
    
    @Override
    public void receiveRequest(String fileName, String IP, int port, String regName) throws RemoteException {
        if (fileControl.fileExists(fileName)) {
            try {
                Registry reg = LocateRegistry.getRegistry(IP, port);
                Service service = (Service) reg.lookup(regName);
                if (!service.isFileAlreadyThere(fileName)) {
                    service.receiveFile(fileName, fileControl.getFileContent(fileName));
                }
            } catch (NotBoundException | RemoteException e) {
                System.err.println("Error sending file! " + e.getMessage());
            }
        } else {
            
        }
    }
    
    @Override
    public boolean isFileAlreadyThere(String fileName) throws RemoteException {
        return fileControl.fileExists(fileName);
    }
    
    @Override
    public void receiveFile(String fileName, byte[] content) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

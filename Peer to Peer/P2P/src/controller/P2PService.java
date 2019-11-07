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
import java.util.List;
import model.Node;

/**
 *
 * @author Rafael Braz
 */
public class P2PService extends UnicastRemoteObject implements Service {
    
    private final Node localNode;
    private final FileControl fileControl;
    private final NodeControl nodeControl;
    
    public P2PService(String dirPath, String IP, int port, String regName, List<Node> nodes) throws RemoteException {
        super();
        fileControl = new FileControl(dirPath);
        nodeControl = new NodeControl(nodes);
        localNode = new Node(IP, port, regName);
    }
    
    @Override
    public void receiveRequest(String fileName, Node sourceNode) throws RemoteException {
        if (fileControl.fileExists(fileName)) {
            try {
                Registry reg = LocateRegistry.getRegistry(sourceNode.getIP(), sourceNode.getPort());
                Service service = (Service) reg.lookup(sourceNode.getRegName());
                if (!service.isFileAlreadyThere(fileName)) {
                    service.receiveFile(fileName, fileControl.getFileContent(fileName));
                }
            } catch (NotBoundException | RemoteException e) {
                System.err.println("Error sending file! " + e.getMessage());
            }
        } else {
            sendRequest(fileName, sourceNode);
        }
    }
    
    @Override
    public boolean isFileAlreadyThere(String fileName) throws RemoteException {
        return fileControl.fileExists(fileName);
    }
    
    @Override
    public void receiveFile(String fileName, byte[] content) throws RemoteException {
        fileControl.addFile(fileName, content);
    }
    
    @Override
    public void startRequest(String fileName) throws RemoteException {
        sendRequest(fileName, localNode);
    }
    
    private void sendRequest(String fileName, Node sourceNode){
        nodeControl.getNodes().parallelStream().forEach((t) -> {
            try {
                Registry reg = LocateRegistry.getRegistry(t.getIP(), t.getPort());
                Service service = (Service) reg.lookup(t.getRegName());
                service.receiveRequest(fileName, sourceNode);
            } catch (NotBoundException | RemoteException e) {
                System.err.println("Error sending request! " + e.getMessage());
            }
        });
    }
    
}

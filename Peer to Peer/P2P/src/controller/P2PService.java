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
    private static FileControl fileControl = null;
    private final List<Node> connectedNodes;

    public P2PService(String dirPath, Node localNode, List<Node> connectedNodes) throws RemoteException {
        super();
        if (fileControl == null){
            fileControl = new FileControl(dirPath);
        }        
        this.connectedNodes = connectedNodes;
        this.localNode = localNode;
    }

    @Override
    public void receiveRequest(String fileName, Node sourceNode) throws RemoteException {
        System.out.printf("Request for file '%s' from %s received.\n", fileName, sourceNode.toString());
        if (!sourceNode.toString().equals(localNode.toString())) {
            if (fileControl.fileExists(fileName)) {
                try {
                    Registry reg = LocateRegistry.getRegistry(sourceNode.getIP(), sourceNode.getPort());
                    Service service = (Service) reg.lookup(sourceNode.getRegName());
                    if (!service.isFileAlreadyThere(fileName)) {
                        service.receiveFile(fileName, fileControl.getFileContent(fileName));
                        System.out.printf("File '%s' sent to %s.\n", fileName, sourceNode.toString());
                    }
                } catch (Exception e) {
                    System.err.printf("Error sending file '%s' to %s! Message: %s\n", fileName, sourceNode.toString(), e.getMessage());
                }
            } else {
                sendRequest(fileName, sourceNode);
            }
        }
    }

    @Override
    public boolean isFileAlreadyThere(String fileName) throws RemoteException {
        System.out.printf("Action check for file '%s' received.\n", fileName);
        return fileControl.fileExists(fileName);
    }

    @Override
    public void receiveFile(String fileName, byte[] content) throws RemoteException {
        fileControl.addFile(fileName, content);
        System.out.printf("File '%s' received.\n", fileName);
    }

    @Override
    public void startRequest(String fileName) throws RemoteException {
        System.out.printf("Starting request for file '%s'.\n", fileName);
        sendRequest(fileName, localNode);
    }

    private void sendRequest(String fileName, Node sourceNode) {
        connectedNodes.forEach((t) -> {
            try {                               
                Registry reg = LocateRegistry.getRegistry(t.getIP(), t.getPort());                
                Service service = (Service) reg.lookup(t.getRegName());                
                service.receiveRequest(fileName, sourceNode);
                System.out.printf("Request sent to %s.\n", t.toString());
            } catch (Exception e) {
                System.err.printf("Error sending request to %s! Message: %s\n", t.toString(), e.getMessage());
            }
        });
    }

}

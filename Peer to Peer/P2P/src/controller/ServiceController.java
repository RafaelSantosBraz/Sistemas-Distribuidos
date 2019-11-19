/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.rmi.server.*;
import model.Node;

/**
 *
 * @author Rafael Braz
 */
public class ServiceController {

    //<editor-fold defaultstate="collapsed" desc="SINGLETON">
    private static ServiceController instance;

    private ServiceController() {

    }

    public static ServiceController getInstance() {
        if (instance == null) {
            instance = new ServiceController();
        }
        return instance;
    }
    //</editor-fold>

    private P2PService service;

    public Node createNode(String IP, int port, String regName) {
        return new Node(IP, port, regName);
    }

    public List<Node> createNodeList(Node... nodes) {
        return Arrays.asList(nodes);
    }

    public boolean createService(String dirPath, Node localNode, List<Node> connectedNodes) {
        try {
            service = new P2PService(dirPath, localNode, connectedNodes);
            System.setProperty("java.rmi.server.hostname", localNode.getIP());
            Registry reg = LocateRegistry.createRegistry(localNode.getPort());            
            reg.rebind(localNode.getRegName(), service);
            System.out.println("RMI server runnig!");
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean startRequest(String fileName) {
        try {
            service.startRequest(fileName);
            return true;
        } catch (RemoteException ex) {
            return false;
        }
    }

    public void notifyNewFile() {
        ViewController.getInstance().notifyNewFile();
    }
}

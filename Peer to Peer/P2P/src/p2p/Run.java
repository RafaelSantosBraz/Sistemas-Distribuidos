/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import controller.P2PService;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;
import model.Node;

/**
 *
 * @author Rafael Braz
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // host
        Node localNode = new Node("192.168.10.100", 9876, "HOST");
        Node externNode = new Node("192.168.10.101", 9875, "INV");
        List<Node> nodes = new ArrayList<>();
        nodes.add(externNode);
        try {
            P2PService service = new P2PService("D:\\GitHub\\Sistemas-Distribuidos\\Peer to Peer\\P2P\\serverfiles", localNode, nodes);            
            LocateRegistry.createRegistry(localNode.getPort()).rebind(localNode.getRegName(), service);
            System.out.println("RMI server runnig!");  
            service.startRequest("magic.txt");
        } catch (RemoteException e) {
            System.err.println("Error running RMI server! " + e.getMessage());
        }
        //invited
//        Node localNode = new Node("192.168.10.101", 9875, "INV");
//        Node externNode = new Node("192.168.10.100", 9876, "HOST");
//        List<Node> nodes = new ArrayList<>();
//        nodes.add(externNode);
//        try {
//            P2PService service = new P2PService("/home/rafael/Downloads/server", localNode, nodes);
//            LocateRegistry.createRegistry(localNode.getPort()).rebind(localNode.getRegName(), service);
//            System.out.println("RMI server runnig!");
//            
//        } catch (RemoteException e) {
//            System.err.println("Error running RMI server! " + e.getMessage());
//
//        }
    }

}

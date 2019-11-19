/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * @author Fábio Moreno
 */
public class BroadcastingClient {
   private static DatagramSocket socket = null;
 
    public static void main(String[] args) throws IOException {
        broadcast("", InetAddress.getByName("172.16.255.255"));        
    }
 
    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);
 
        byte[] buffer = broadcastMessage.getBytes();
 
        DatagramPacket packet  = new DatagramPacket(buffer, buffer.length, address, 9876);
        socket.send(packet);
        socket.close();
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multicast;

import java.io.IOException;

/**
 *
 * @author Fábio Moreno
 */
public class MulticastMain {
        public static void main(String[] args) throws IOException {
        // TODO code application logic here
        MulticastServidor m = new MulticastServidor();
        m.multicast("testando multicast");
        
    }
}

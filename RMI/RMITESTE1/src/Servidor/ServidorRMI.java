/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import RMI.ServerImpl;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Fábio Moreno
 */
public class ServidorRMI {
        public static void main(String args[]){
        try{
            ServerImpl a = new ServerImpl();
            Registry reg = LocateRegistry.createRegistry(9876);
            reg.rebind("serverR", a);
            System.out.println("Servidor Iniciado");
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    
}

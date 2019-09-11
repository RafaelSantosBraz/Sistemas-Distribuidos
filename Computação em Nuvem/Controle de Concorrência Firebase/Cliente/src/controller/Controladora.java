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

/**
 *
 * @author Rafael Braz
 */
public class Controladora {

    private Servico servico;

    public Controladora() {
        servico = null;
        conectar();
    }

    private void conectar() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 9876);
            servico = (Servico) reg.lookup("R");
        } catch (NotBoundException | RemoteException e) {
            servico = null;
        }
    }

    public Servico getServico() {
        return servico;
    }
}

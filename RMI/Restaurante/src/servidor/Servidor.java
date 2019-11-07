/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import servico.ServEntrega;

/**
 *
 * @author aluno
 */
public class Servidor {

    public static void main(String[] args) {
        try {
            ServEntrega a = new ServEntrega();
            Registry reg = LocateRegistry.createRegistry(9876);
            reg.rebind("serverR", a);
            System.out.println("Servidor Iniciado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

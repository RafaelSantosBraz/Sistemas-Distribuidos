/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import classes.Aluno;
import controller.ControleEspera;
import controller.Requisicao;
import controller.ServicoServidor;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Braz
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServicoServidor servico = new ServicoServidor();
            LocateRegistry.createRegistry(9876).rebind("R", servico);
            System.out.println("Servidor Iniciado!");
        } catch (RemoteException e) {
            System.err.println("Erro ao iniciar o servidor RMI! " + e.toString());
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import controller.ServicoBanco;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class Run {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServicoBanco servico = new ServicoBanco();
            Registry reg = LocateRegistry.createRegistry(9876);
            Scanner leitor = new Scanner(System.in);
            System.out.println("Informe o nome do Banco para registro: ");
            String nomeBanco = leitor.nextLine();
            reg.rebind(nomeBanco, servico);
            System.out.println("Servidor Iniciado!");
            // implementar requisição de código e sincronizar com o BD

        } catch (RemoteException e) {
            System.err.println("Erro ao iniciar o servidor RMI do Banco! " + e.toString());
        } catch (SQLException ex) {
            System.err.println("Erro ao iniciar o serviço MYSQL do Banco! " + ex.toString());
        }
    }

}

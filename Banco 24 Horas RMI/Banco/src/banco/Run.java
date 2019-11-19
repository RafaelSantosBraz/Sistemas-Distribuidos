/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import controller.ServicoBanco;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import transmissao.ConexaoBanco;

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
            ServicoBanco servico = new ServicoBanco("localhost", "3306", "banco", "mysql", "mysql");
            System.out.println("Informe o código para execução do Banco (0 - BB, 1 - Caixa): ");
            Scanner leitor = new Scanner(System.in);
            switch (leitor.nextInt()) {
                case ConexaoBanco.BB:
                    LocateRegistry.createRegistry(9876).rebind("BB", servico);
                    System.out.println("Servidor Iniciado!");
                    break;
                case ConexaoBanco.CAIXA:
                    LocateRegistry.createRegistry(9875).rebind("Caixa", servico);
                    System.out.println("Servidor Iniciado!");
                    break;
                default:
                    System.err.println("Código Inválido!");
            }
        } catch (RemoteException e) {
            System.err.println("Erro ao iniciar o servidor RMI do Banco! " + e.toString());
        }
    }

}

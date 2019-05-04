/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmissao;

import controller.Servico;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author rafael
 */
public class ConexaoBanco {

    private Servico servico;

    public ConexaoBanco(String nomeBancoConexao, String IP, int numero) throws Exception {
        conectar(nomeBancoConexao, IP, numero);
    }

    private void conectar(String nomeBancoConexao, String IP, int numero) throws Exception {
        Registry reg = LocateRegistry.getRegistry(IP, numero);
        servico = (Servico) reg.lookup(nomeBancoConexao);
    }

    public Servico getServico() {
        return servico;
    }

}

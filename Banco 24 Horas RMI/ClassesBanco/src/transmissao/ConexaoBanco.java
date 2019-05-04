/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transmissao;

import controller.Servico;
import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author rafael
 */
public class ConexaoBanco implements Serializable {

    public static final int BB = 0;
    public static final int CAIXA = 1;

    private Servico servico;

    public ConexaoBanco(int codBanco) throws Exception {
        switch (codBanco) {
            case BB:
                conectar("BB", "192.168.10.102", 9876);
                break;
            case CAIXA:
                conectar("Caixa", "192.168.10.100", 9875);
        }
    }

    private void conectar(String nomeBancoConexao, String IP, int numero) throws Exception {
        Registry reg = LocateRegistry.getRegistry(IP, numero);
        servico = (Servico) reg.lookup(nomeBancoConexao);
    }

    public Servico getServico() {
        return servico;
    }

}

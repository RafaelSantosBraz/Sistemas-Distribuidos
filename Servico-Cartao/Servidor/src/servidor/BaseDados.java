/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author rafael
 */
public class BaseDados {

    private final HashMap<String, Boolean> base;

    public BaseDados() {
        base = new HashMap<>();
        preencher();
    }

    public void adicionar(String chave, Boolean aceitar) {
        base.put(chave, aceitar);
    }

    public Boolean buscar(String chave) {
        Boolean resultado = base.get(chave);
        return resultado == null ? false : resultado;
    }

    private void preencher() {
        try {
            FileReader arquivo = new FileReader("/media/rafael/Dados Compartilhados/GitHub/Sistemas-Distribuidos/Servico-Cartao/Servidor/base.txt");
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                Boolean status = false;
                if (partes[2].equals("true")) {
                    status = true;
                }
                adicionar(partes[0] + partes[1], status);
            }
        } catch (Exception e) {
            System.out.println("Erro de Arquivo! " + e.toString());
        }

    }
}

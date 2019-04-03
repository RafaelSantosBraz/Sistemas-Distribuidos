/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class BaseDados {

    private final HashMap<Integer, Double> base;

    public BaseDados() {
        base = new HashMap<>();
        preencher();
    }

    public void adicionar(Integer chave, Double valor) {
        base.put(chave, valor);
    }

    public Double buscar(Integer chave) {
        return base.get(chave);
    }

    private void preencher() {
        try {
            FileReader arquivo = new FileReader("/home/a120121/Área de Trabalho/Sistemas-Distribuidos/Servico-Cartao/Servidor/base.txt");
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            while ((linha = leitor.readLine()) != null) {
                String[] partes = linha.split(";");
                adicionar(Integer.valueOf(partes[0]), Double.valueOf(partes[1]));
            }
        } catch (Exception e) {
            System.out.println("Erro de Arquivo! " + e.toString());
        }
    }

    public void atualizarArquivo() {
        try {
            File temp = new File("/home/a120121/Área de Trabalho/Sistemas-Distribuidos/Servico-Cartao/Servidor/base.txt");
            temp.delete();            
            FileWriter arquivo = new FileWriter("/home/a120121/Área de Trabalho/Sistemas-Distribuidos/Servico-Cartao/Servidor/base.txt", true);
            BufferedWriter escritor = new BufferedWriter(arquivo);
            base.forEach((t, u) -> {
                try {
                    escritor.append(t.toString() + ";" + u.toString() + "\n");
                } catch (IOException ex) {
                    System.out.println("Erro: " + ex.toString());
                }
            });
            escritor.close();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.toString());
        }
    }
}
